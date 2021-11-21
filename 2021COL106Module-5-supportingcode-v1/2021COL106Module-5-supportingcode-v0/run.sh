javac testercode.java
java testercode > in1
if !(diff -w -q in1 out)
    then
        echo "Wrong,Sad."
        # echo "======Expected====="
        # cat out
        # echo "======Your-Output======"
        # cat in1
        exit
    fi
echo "Correct,Happy."
rm in1