# To_Do_List_Project

1. Introduction
This project will test your skills on android basic topics covered till now in the class.

2. Objective
This project will help you to master the following concepts:
• activity
• intent
• menu
• dialogs
• database

5. Problem Statement
5.1 What need to be implemented?
5.1.1 TO-DO Database : Create table with following entries.
KEY_ID ---> INTEGER PRIMARY KEY
KEY_TITLE ---> TEXT
KEY_DESCRIPTION ---> TEXT
KEY_DATE ----> TEXT
KEY_STATUS ----> INTEGER
Here KEY_STATUS will store value 0 by default whenever new task will be created marking
the new task as incomplete.
5.1.2 TO-DO List screen
a. Activity should extend the AppCompactActivity class.
b. The layout should have a ListView in the XML and the same XML page should be set
as the content View in the onCreate() method.
c. Each list item will have 3 TextViews and an ImageView.
I. TextView 1: For the Title of the To-Do Task.
II. TextView 3: Description of the To-Do Task.
III. TextView 2: Timestamp or due date of the To-Do task.
d. The entire list item should be sorted in ascending order based on the due date of
each task. 

5.1.3 Create options menu and dialog
a) Two menu items has to be created and these items have to be placed on the
ActionBar.
b) Upon click on the Add item (plus icon) a dialog should pop-up asking for the details
from the user. 
c) The above dialog should contain two EditTexts, a DatePicker and two Buttons.
d) The EditTexts should at least have a minimum characters entered (Validation has to
be taken care).
e) Upon click on save button the details has to be saved to the database.
f) Upon clicking on the list item, an AlertDialog box should be popped out with the prepopulated
values of the list item clicked.
g) If the changes are made to the list item and the same has to reflected in the ListView
with the database.
h) Upon the Long clicked on the List Item, items have to be marked as the completed
task "1" i.e. if in database the KEY_STATUS for that particular task is marked as zero
and vice versa.
i) Upon the click of second menu item a new Activity has to be display which will be
having a task list of only completed task.
j) In the completed task list activity you should be able to delete the task on long press. 

  I have added Apk file and screenshots of the output has been added .
  
  I have done all the tasks that were asked to do in the project .
  
  I have run this app on device Letv Le X509 (Android 6.0, API 23) .

