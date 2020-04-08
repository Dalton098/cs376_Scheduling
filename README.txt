Instructions:
 -   To compile the files run javac *.java 
     from a terminal while in the directory containing the files
 -   Then to run do: java Driver input_file [FCFS|RR|SJF] [time_quantum] 
	-Where input_file is the file with the data for the PCBs 
	-FCFS|RR|SJF is the scheduling algorithm selected 
	-time_quantum is only used when RR is selected which will be the time
	quantum used for RR

Input Files:
	- input1.txt: The example provided in the instructions
	- input2.txt: A simple example to demonstrate CPU usage is properly tracking


Driver
Takes in command line argument of the form:java Driver input_file [FCFS|RR|SJF] [time_quantum] 
Controls input towards the scheduling schemes and creating the scheduler object

PCB 
Class that holds relevant information for the processes that will be
executed in the scheduling algorithms. Acts as a container of data and
performs some calcuations regarding the information inside the class such as
wait time.

Scheduler
Class that is responsible for running the specified
scheduling scheme. Apart of the strategy pattern where this
class determines the algorithm that will be ran

Scheduling Scheme
Abstract Class that outlines some of the necessary member data and functions
for the scheduling algorithms to have. Also, comes with generalized functions
for calculating average wait time and average turnaround time

FCFS
First come first serve scheduling algorithm that extends the SchedulingScheme.
The runProcess() method implements the first come first serve scheduling algorithm
using the member data of the object.

PE_SJF
Pre-emptive shortest job first scheduling algorithm that extends the SchedulingScheme.
The runProcess() method implements the pre-emptive shortest job first scheduling algorithm
using the member data of the object.

RR
Pre-emptive Round Robin scheduling algorithm that extends the SchedulingScheme.
The runProcess() method implements the pre-emptive round robin algorithm
using the member data of the object.