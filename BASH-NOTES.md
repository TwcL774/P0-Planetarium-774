press ESC then I to write to file using vim.
Enter creates a new line.
Backspace removes the new line.

to save and quite do :wq

Press ESC to change mode.
user the echo keyword to write to file
        echo "text to write" > fileName //truncates then write to file
        echo "text to write" >> fileName //appends to file
testing \n new line
echo -e text n text to create a new line


cd / changes to root directory
cd - changes to home directory
cd or cd ~ changes to current user directory
cd ../ changes to parent directory
cd ./ resolves in the current directory

pip | 

grep - filter information from a file

<!-- grabs all responses-->
grep "execution took" activityLogs.log >> response.log
<!-- total number of responses -->
grep -c "execution took" response.log > response
<!-- total number of unsucceful responses-->
grep -c "Server Error" response.log >> response

<!-- (Total Requests * 0.2) / 100 <= # Failed Requests -->
<!-- total response time / total response = avg must be <= 200 milliseconds -->