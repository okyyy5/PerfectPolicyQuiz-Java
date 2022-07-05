# PerfectPolicyQuiz-Java
A Java application that allows the sending of quizzes to clients through a local network.

### Description

This application's function is to allow the loading of quiz data from a txt file and to send these questions to clients over a local network. The scenario is, through this application, a user will be able to test employees by sending them quiz questions over a local network and recieve responses back regarding if they answered correctly or not. These responses will be displayed in a Doubly Linked List that visually updates after another question is sent.

### Features

The main table of the master client can be searched through by question number, topic, or subtopic. The table can also be sorted (through an **Insertion Sort**, **Bubble Sort**, or **Selection Sort** algorithm) by question number, topic, or subtopic. 

When a question is sent to a slave client by the master client, the question is also stored in a Binary Tree. This **Binary Tree** can be traversed **In-Order**, **Post-Order**, or **Pre-Order**. This Binary Tree can also be saved with a **Hashing Algorithm**

After the slave client/s answers a sent question and another question is sent by the master client, the **Doubly Linked List** will update by displaying the response/s for the previous question in the following format:
  
  - QuestionNumber - QuestionTopic(QuestionSubTopic) - ResponsesCorrect/TotalResponses

This application was developed as a way to explore different aspects of development in Java such as:
  - Sorting Algorithms
  - Doubly Linked Lists
  - Binary Trees and Traversals
  - Threads, Sockets, and Networking
  - Hashing Techniques
  - Operating System Signals
  - Third-Party Library Utilization

### Technologies Used

Developed in IntelliJ with The Java Programming Language.

The external library ApacheCommonsLang3 was utilized to process strings more efficiently in the Main Form.

### How to Use and Install

There is no executable, therefore it must be imported into a relevant environment that can run Java code.

The application's various clients can be run individually, however, to run the application as a whole, the ChatServer, Main, and MainEmployee methods must be run. Afterwards, they can be connected by pressing the Connect button on the Master Client that is opened by the Main method.

Consult the Technical Report for the User Manual for further information.

### Credits

<a href="https://www.linkedin.com/in/oktay-turdu/">Oktay Turdu</a>

### Licence

GNU General Public License v3.0
