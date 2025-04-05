#!/bin/bash

echo "Attempting to compile java code..."
javac -d . ../../*.java
javac Main.java
runner="java -cp . Main"

error=0

# Correct test cases
correctCount=$(find "Correct/" -maxdepth 1 -type f -regex  ".*/[0-9]+\.code" | wc -l)
for ((i = 1; i <= $correctCount; i++))
do
	echo ""
	echo "Running ${i}.code"
	timeout 5 ${runner} Correct/${i}.code > Correct/${i}.student
	#Check for correct print
	tr -d '[:space:]' < Correct/${i}.student > Correct/temp1
	tr -d '[:space:]' < Correct/${i}.code > Correct/temp2
	echo "Comparing input and output"
	if cmp -s "Correct/temp1" "Correct/temp2"; then
		echo "Print looks good"
		correctScore=$(($correctScore + 1))
	else
		echo "Student output and expected output are different"
	fi
done

rm Correct/temp1
rm Correct/temp2

echo "----------------"
echo "Running error cases:"
echo "----------------"

errorCount=$(find "Error/" -maxdepth 1 -type f -regex  ".*/[0-9]+\.code" | wc -l)
for ((i = 1; i <= $errorCount; i++))
do
	echo ""
	echo "Running ${i}.code"
	timeout 5 ${runner} Error/${i}.code Error/${i}.data > Error/${i}.student
	echo "Running diff with ${i}.expected"
	grep -o '[[:digit:]]\+' Error/${i}.student > Error/temp1
	grep -o '[[:digit:]]\+' Error/${i}.expected > Error/temp2
	if cmp -s "Error/temp1" "Error/temp2"; then
		echo "Print looks good"
		errorScore=$(($errorScore + 1))
	else
		echo "Student output and expected output are different"
	fi
done
echo ""

rm Error/temp1
rm Error/temp2

echo "Correct cases score out of $correctCount:"
echo $correctScore
echo "Error cases score out of $errorCount:"
echo $errorScore

echo "Done!"