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

.(space)fileName to run a script

echo Hello World
# -e enables interpretation of \n
echo -e "It's proper to put string to print double quotations\n"

# << sign for making comments
word="Print this strinog"
# $ this sign refers to the variable created
echo $word

# do arithmetic in echo
numOne=13
numTwo=17
# prints numOne and numTwo
echo #numOne + $numTwo
# prints the sum of numOne and numTwo
echo $((numOne + numTwo))
# this finds the sum and save it to variable sum
sum=$((numOne + numTwo))
# prints sum
echo $sum

# do arithmetic with floating values
floatOne=132.56
floatTwo=415.15
result=$(echo "scale=2; $floatOne + $floatTwo")
echo $result



pip | 

grep - filter information from a file

<!-- grabs all responses-->
grep "execution took" activityLogs.log >> allResponse
<!-- total number of responses -->
grep -c "execution took" response.log > response
<!-- total number of unsucceful responses-->
grep -c "Server Error" response.log >> response
<!-- parse lines to decimal only -->
<!-- -E for extended regex -o to print only matching -->
grep -Eo '[[:digit:]]+[.][[:digit:]]+' response.log >> response
<!-- finds total by adding each line then divided by total number of records to get average-->
grep -Eo '[[:digit:]]+[.][[:digit:]]+' response.log | awk '{sum+=$1} END {print sum/NR}'

grep "^Response.*execution took" activityLogs.log > allResponse && grep -c "execution took" activityLogs.log > response && grep -c "Server Error" activityLogs.log >> response && grep -Eo '[[:digit:]]+[.][[:digit:]]+' allResponse | awk '{sum+=$1} END {print sum/NR}' >> response && rm allResponse && head -n 1 response | bc -l <<< ""




<!-- (Total Requests * 0.2) / 100 <= # Failed Requests -->
<!-- total response time / total response = avg must be <= 200 milliseconds -->

#!/bin/bash

# Use grep to search for a line that starts with "Response" and includes "execution took"
# in the activityLogs.log file, and save the results to a new file called allResponse
grep "^Response.*execution took" ./activityLogs.log > allResponses

# Use grep to count the number of lines that include "execution took" in the activityLogs.log file
# and save the result to a new file called sliSummary file
# Total # of responses
grep -c "^Response.*execution took" ./activityLogs.log > sliSummary

# Use grep to count the number of lines that include "Server Error" in the activityLogs.log file
# and append the result to the end of the sliSummary file
# Total # of error responses
grep -c "^Response.*Server Error" ./activityLogs.log >> sliSummary

# Grabs total number of responses from sliSummary file
# piped to awk
# calculate min # of error for 99.8 success rate
# piped to 2nd awk
# compare # of error to min # of error from sliSummary file
# if # of error is <= min # of error, 99.8 success rate is achieved
grep -m 1 -o ".*" sliSummary | awk '{ print $0 * 0.2 / 100 }' | awk '{if ($0 >= "head -n 2 sliSummary | tail -n 1") print "true"; else print "false"}' >> sliSummary

# Use grep and awk to calculate the average execution time from the allResponse file and
# append the result to the end of the sliSummary file
grep -Eo '[[:digit:]]+[.][[:digit:]]+' ./allResponses | awk '{sum+=$1} END {print sum/NR}' >> sliSummary

# Delete the allResponses file
rm ./allResponses

# # Use grep to search for a line that starts with "Response" and includes "execution took"
# # in the activityLogs.log file, and save the results to a new file called allResponse
# grep "^Response.*execution took" ./activityLogs.log > allResponses

# # Use grep to count the number of lines that include "execution took" in the activityLogs.log file
# # and save the result to a new file called sliSummary file
# # Total # of responses
# grep -c "^Response.*execution took" ./activityLogs.log > sliSummary

# # Use grep to count the number of lines that include "Server Error" in the activityLogs.log file
# # and append the result to the end of the sliSummary file
# # Total # of error responses
# grep -c "^Response.*Server Error" ./activityLogs.log >> sliSummary

# # Grabs total number of responses from sliSummary file
# # piped to awk
# # calculate min # of error for 99.8 success rate
# # piped to 2nd awk
# # compare # of error to min # of error from sliSummary file
# # if # of error is <= min # of error, 99.8 success rate is achieved
# grep -m 1 -o ".*" sliSummary | awk '{ print $0 * 0.2 / 100 }' | awk '{if ($0 >= "head -n 2 sliSummary | tail -n 1") print "true"; else print "false"}' >> sliSummary

# # Use grep and awk to calculate the average execution time from the allResponse file and
# # append the result to the end of the sliSummary file
# grep -Eo '[[:digit:]]+[.][[:digit:]]+' ./allResponses | awk '{sum+=$1} END {print sum/NR}' >> sliSummary

# # Delete the allResponses file
# rm ./allResponses