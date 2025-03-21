#!/bin/bash

runner=$1

echo "Attempting to compile java code..."
javac -d . ../../*.java
javac Main.java
runner="java -cp . Main"

for value in {1..11}
do
	echo ""
	echo "Running ${value}.code"
	timeout 5 ${runner} Correct/${value}.code Correct/${value}.data > Correct/${value}.student
	echo "Running diff with ${value}.expected"
	grep -o '[[:digit:]]\+' Correct/${value}.student > Correct/temp1
	grep -o '[[:digit:]]\+' Correct/${value}.expected > Correct/temp2
	if cmp -s "Correct/temp1" "Correct/temp2"; then
		echo "Print looks good"
		score=$(($score + 1))
	else
		echo "Student output and expected output are different"
	fi
done

rm Correct/temp1
rm Correct/temp2

echo ""
echo "Running error cases:"
echo ""

echo "Running 00.error:"
echo "-----Student Output Starts-----"
timeout 5 ${runner} Error/00.code Error/00.data
echo "-----Student Output Ends-----"
read -n 1 -p "Error procedure body missing (no stmt-seq). Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""

echo "Running 01.error:"
echo "-----Student Output Starts-----"
timeout 5 ${runner} Error/01.code Error/01.data
echo "-----Student Output Ends-----"
read -n 1 -p "Error calling a procedure that has not been declared. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""

echo "Running 02.error:"
echo "-----Student Output Starts-----"
timeout 5 ${runner} Error/02.code Error/02.data
echo "-----Student Output Ends-----"
read -n 1 -p "Error is duplicate formal parameters. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""

echo "Running 03.error:"
echo "-----Student Output Starts-----"
timeout 5 ${runner} Error/03.code Error/03.data
echo "-----Student Output Ends-----"
read -n 1 -p "Semantic error, duplicate procedure name. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi
echo ""
echo ""



echo "Correct cases score out of 11:"
echo $score
echo "Error cases score out of 4:"
echo $error

echo "Done!"