# PisonCodeChallenge

### Background
For this coding challenge, your task will be to implement a TCP client that receives JSON data from a local server and classifies the data. This data vaguely resembles the data received from a Pison device. Your task is to classify “activations” and send a message back over TCP every time you think you’ve classified an activation.

### Instructions SERVER
Run the jar from the command-line like so: 
java -jar .\challenge-0.1.0.jar

The server will print out the *TCP PORT* that it is writing data to.

### Instructions CLIENT
Run the CLIENT
Input the *TCP PORT*

### OUTPUT
The message that is going to be displayed on the SERVER SIDE is:
"X ACTIVATION CLASSIFIED"

X = is the number of activations