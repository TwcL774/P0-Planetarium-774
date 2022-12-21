#!/bin/bash

# get the current directory and assign to a variable
pwd=$(pwd)

# deletes sliSummary
rm $pwd/logs/sliSummary

# grep command with a -c flag to count all matching lines(Total # of Responses) from activityLogs.log
totalResponse=$(grep -c "^Response.*execution took" $pwd/logs/activityLogs.log)

# grep command with a -c flag to count all matching lines(Total # of Error Responses) from activityLogs.log
errorResponse=$(grep -c "^Response.*Server Error" $pwd/logs/activityLogs.log)

# calculate the total # of Successfull Responses
successResponse=$((totalResponse - errorResponse))

# calculate the Success Rate
successRate=$(echo "scale=3; ($successResponse / $totalResponse) * 100" | bc)

# format the Success Rate to 1 decimal point
successRate=$(printf "%.1f" $successRate)

# grep command that grab all matching lines and cut until only the execution time is left
# then save to a file named responseTimings
grep "^Response.*execution took" $pwd/logs/activityLogs.log | cut -f 2 -d ',' | cut -f 4 -d ' ' > $pwd/logs/responseTimings

# reads responseTiming line by line while adding each line
# calculate the average by line+=line divided by total number of lines
avgTiming=$(awk '{sum+=$1} END {print sum/NR}' $pwd/logs/responseTimings)

# format the Average Timing to 2 decimal points
avgTiming=$(printf "%.2f" $avgTiming)

# writes the values of the variable into a file named sliSummarry
echo -e "$totalResponse\n$successResponse\n$errorResponse\n$successRate%\n$avgTiming" > $pwd/logs/sliSummary

# if else statement that checks SLI against SLO
# SLO Success Rate must be equal or greater than 99.8%
# and Response Time equal or less than 200 ms
if (( $(echo "$successRate >= 99.8" | bc) ))
then
	if (( $(echo "$avgTiming <= 200" | bc) ))
	then
		echo -e "$successRate% of requests is successful within $avgTiming ms\nTrue\nTrue" >> $pwd/logs/sliSummary
	else
		echo -e "$successRate% of requests is successful within $avgTiming ms\nTrue\nFalse" >> $pwd/logs/sliSummary
	fi
else
	if (( $(echo "$avgTiming > 200" | bc) ))
        then
                echo -e "$successRate% of requests is successful within $avgTiming ms\nFalse\nFalse" >> $pwd/logs/sliSummary
        else
                echo -e "$successRate% of requests is successful within $avgTiming ms\nFalse\nTrue" >> $pwd/logs/sliSummary
	fi
fi

# deletes responseTimings file
rm $pwd/logs/responseTimings
