import os as os
import sys as sys
import shutil as shu
topdir = sys.argv[1].strip()
exts = sys.argv[2][1:len(sys.argv[2])-1].replace(" ", "").strip().split(",")
copydir = sys.argv[3].strip()
numfiles = 0
large_files = []
copy_files = []
copy_files_rename = []
try:
    os.mkdir(copydir)
except:
    print("Error creating copy directory. Does it already exist?")
for dirpath, dirnames, files in os.walk(topdir):
    for name in files:
        if os.stat(os.path.join(dirpath, name)).st_size < 524288000:
            for ext in exts:
                if name.lower().endswith(ext.lower()) or ext == ".*":
                    print(os.path.join(dirpath, name))
                    copy_files.append(os.path.join(dirpath, name))
                    copy_files_rename.append(copydir + "\\" + str(0) + str(numfiles) + "_" + name)
                    numfiles += 1
        else:
            large_files.append(os.path.join(dirpath, name))         
print("\nTotal files found: " + str(numfiles))
print("\nFiles too large:")
try:
    lf_size = len(large_files)
    print("Files found: " + str(lf_size))
    for i in range(0, lf_size):
        print(" > " + large_files[i])
except:
    print("<> No Files Found <>") 
print("Copying Files...")
for i in range(0, len(copy_files)):
    print(str(i) + "/" + str(len(copy_files)) + " : " + str(int((i / len(copy_files)) * 100)) + "%")
    shu.copyfile(copy_files[i], copy_files_rename[i])
print(str(len(copy_files)) + "/" + str(len(copy_files)) + " : " + "100%")
lf = open(copydir + "/000_0utput.log", 'w')
lf.write("Byte Limit: 524288000 (1/2 GB)\n\n")
for i in range(0, len(copy_files)):
    lf.write(copy_files[i] + " : " + str(os.stat(copy_files[i]).st_size) + "\n")
lf.close
print("Log file created.")