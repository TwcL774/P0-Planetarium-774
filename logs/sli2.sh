#!/bin/bash

# deletes sliSummary
rm sliSummary2

# grep command with a -c flag to count all matching lines(Total # of Responses) from activityLogs.log
totalResponse=$(grep -c "^Response.*execution took" activityLogs.log)

# grep command with a -c flag to count all matching lines(Total # of Error Responses) from activityLogs.log
errorResponse=$(grep -c "^Response.*Server Error" activityLogs.log)

# calculate the total # of Successfull Responses
successResponse=$((totalResponse - errorResponse))

# calculate the Success Rate
successRate=$(echo "scale=3; ($successResponse / $totalResponse) * 100" | bc)

# format the Success Rate to 1 decimal point
successRate=$(printf "%.1f" $successRate)

# grep command that grab all matching lines and cut until only the execution time is left
# then save to a file named responseTimings
grep "^Response.*execution took" activityLogs.log | cut -f 2 -d ',' | cut -f 4 -d ' ' > responseTimings

# reads responseTiming line by line while adding each line
# calculate the average by line+=line divided by total number of lines
avgTiming=$(awk '{sum+=$1} END {print sum/NR}' responseTimings)

# format the Average Timing to 2 decimal points
avgTiming=$(printf "%.2f" $avgTiming)

# writes the values of the variable into a file named sliSummarry
echo -e "$totalResponse\n$successResponse\n$errorResponse\n$successRate%\n$avgTiming" > sliSummary2

# if else statement that checks SLI against SLO
# SLO Success Rate must be equal or greater than 99.8%
# and Response Time equal or less than 200 ms
if (( $(echo "$successRate >= 99.8" | bc) ))
then
	if (( $(echo "$avgTiming <= 200" | bc) ))
	then
		echo -e "$successRate% of requests is successful within $avgTiming ms\nTrue\nTrue" >> sliSummary2
	else
		echo -e "$successRate% of requests is successful within $avgTiming ms\nTrue\nFalse" >> sliSummary2
	fi
else
	if (( $(echo "$avgTiming > 200" | bc) ))
        then
                echo -e "$successRate% of requests is successful within $avgTiming ms\nFalse\nFalse" >> sliSummary2
        else
                echo -e "$successRate% of requests is successful within $avgTiming ms\nFalse\nTrue" >> sliSummary2
	fi
fi

# deletes responseTimings file
rm responseTimings
