import os
dir_path = "/home/nihalzown/Downloads"
extentions =( ".jpg", ".jpeg", ".png", ".pdf")
if not os.path.exists(dir_path):
    print("Directory not found")
else:
    matched_files = [f for f in os.listdir(dir_path) if f.endswith(extentions)]
    if matched_files:
        print("Files found:")
        for file in matched_files:
            print(file)
    else:
        print("No files found")
