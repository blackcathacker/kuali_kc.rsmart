#!/usr/bin/env ruby
require 'fileutils'

@patches_basedir = "cx_patches"
@patch_mapping   = "#{@patches_basedir}/patch_mapping.csv"

def self.create_patch(file)
  dir_name   = File.dirname  file
  base_name  = File.basename file
  patch_dir  = "#{@patches_basedir}/#{dir_name}"
  FileUtils.mkdir_p "#{patch_dir}"
  patch_file = "#{patch_dir}/#{base_name}.patch"
  system "git diff -w kc-release-5_2_1-tag..rsmart-5.2.1 -- #{file} > #{patch_file}"
end

FileUtils.mkdir_p @patches_basedir

File.open(@patch_mapping, "w") do |csv|
  csv.write "original_location,new_location\n"
  File.open("git_diff_kc-release-5_2_1-tag..rsmart-5.2.1_stat.txt", "r").readlines.each do |line|
    orig_file_path = line.chomp
    create_patch orig_file_path

    base_name = File.basename orig_file_path
    matches_found = 0
    path_found = ""
    Dir.glob "**/#{base_name}" do |file_path|
      matches_found += 1
      path_found = file_path
    end
    if matches_found == 1
      csv.write "\"#{orig_file_path}\",\"#{path_found}\"\n"
    else
      csv.write "-->\"#{orig_file_path}\",\"#{matches_found}\"\n"
    end
  end
end
