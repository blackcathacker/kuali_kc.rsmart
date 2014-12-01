#!/usr/bin/env ruby
require 'csv'
require 'fileutils'
require 'logger'
require 'tempfile'

@patches_basedir = "cx_patches"
@patch_mapping   = "#{@patches_basedir}/patch_mapping.csv"

logger = Logger.new(STDOUT)
dev_null = "/dev/null"

CSV.open(@patch_mapping, headers: :first_row, header_converters: :symbol) do |csv|
  csv.find_all do |row|
    original_file_path = row[:original_location]
    new_file_path      = row[:new_location]
    logger.info "Remap: '#{original_file_path}' --> '#{new_file_path}'"

    patch_file = "#{@patches_basedir}/#{original_file_path}.patch"
    Tempfile.create "revised_patch_file" do |revised_patch_file|
      left = right = ""
      File.foreach(patch_file) do |line|
        if line =~ /^(---\s+a\/)(.+)$/
          left = $2
          if new_file_path.eql? dev_null
            revised_patch_file.write line
          else
            revised_patch_file.write "--- a/#{new_file_path}\n".gsub(/[ab]+\/\/dev\/null/, dev_null)
          end
        elsif line =~ /^(\+\+\+\s+b\/)(.+)$/
          right = $2
          revised_patch_file.write "+++ b/#{new_file_path}\n".gsub(/[ab]+\/\/dev\/null/, dev_null)
        elsif line =~ /^---\s+\/dev\/null$/
          left = dev_null
          revised_patch_file.write line
        elsif line =~ /^\+\+\+\s+\/dev\/null$/
          right = dev_null
          revised_patch_file.write line
        elsif line =~ /^Binary\s+files\s+(.+)\s+and\s+(.+)\s+differ$/
          left = $1
          right = $2
          if dev_null.eql? left
            revised_patch_file.write "Binary files #{left} and b/#{new_file_path} differ\n"
          elsif dev_null.eql? right
            revised_patch_file.write "Binary files a/#{new_file_path} and #{right} differ\n"
          end
          left.gsub!(/^(a\/|b\/)/, '')
          right.gsub!(/^(a\/|b\/)/, '')
        else
          revised_patch_file.write line
        end
      end # line
      # logger.info("'#{left}' != '#{right}'") unless left.eql?(right)
      # logger.error("left  #{left} != #{original_file_path}") unless original_file_path.eql?(left)
      # logger.error("right #{right} != #{original_file_path}") unless original_file_path.eql?(right)
      revised_patch_file.flush
      FileUtils.copy(revised_patch_file.path, patch_file)
    end # revised_patch_file
  end # row
end # csv
