#!/bin/bash
pwd
# deletes sliSummary
rm /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/sliSummary
# grep command with a -c flag to count all matching lines(Total # of Responses) from activityLogs.log
totalResponse=$(grep -c "^Response.*execution took" /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/activityLogs.log)
# grep command with a -c flag to count all matching lines(Total # of Error Responses) from activityLogs.log
errorResponse=$(grep -c "^Response.*Server Error" /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/activityLogs.log)
# calculate the total # of Successfull Responses
successResponse=$((totalResponse - errorResponse))
# calculate the Success Rate
successRate=$(echo "scale=3; ($successResponse / $totalResponse) * 100" | bc)
# format the Success Rate to 1 decimal point
successRate=$(printf "%.1f" $successRate)
# grep command that grab all matching lines and cut until only the execution time is left
# then save to a file named responseTimings
grep "^Response.*execution took" /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/activityLogs.log | cut -f 2 -d ',' | cut -f 4 -d ' ' > /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/responseTimings
# reads responseTiming line by line while adding each line
# calculate the average by line+=line divided by total number of lines
avgTiming=$(awk '{sum+=$1} END {print sum/NR}' /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/responseTimings)
# format the Average Timing to 2 decimal point
avgTiming=$(printf "%.2f" $avgTiming)

# writes the values of the variable into a file named sliSummarry
echo -e "$totalResponse\n$successResponse\n$errorResponse\n$successRate%\n$avgTiming" > /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/sliSummary

# if else statement that checks SLI against SLO
# SLO Success Rate must be equal or greater than 99.8%
# and Response Time equal or less than 200 ms
if (( $(echo "$successRate >= 99.8" | bc) ))
then
	if (( $(echo "$avgTiming <= 200" | bc) ))
	then
		echo -e "$successRate% of requests is successful within $avgTiming ms\nTrue\nTrue" >> /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/sliSummary
	else
		echo -e "$successRate% of requests is successful within $avgTiming ms\nTrue\nFalse" >> /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/sliSummary
	fi
else
	if (( $(echo "$avgTiming > 200" | bc) ))
        then
                echo -e "$successRate% of requests is successful within $avgTiming ms\nFalse\nFalse" >> /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/sliSummary
        else
                echo -e "$successRate% of requests is successful within $avgTiming ms\nFalse\nTrue" >> /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/sliSummary
	fi
fi

# deletes responseTimings file
rm /c/Users/TwcL774/Desktop/P0-Planetarium-774/logs/responseTimings
