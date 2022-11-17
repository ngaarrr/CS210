# **Event Planner**
## *create, organize and keep track of your event*
##### This application allows a host to create and organize an event. The host is able to access and adjust details of the event, as well as keeping track of who will be attending.
##### I created this project because planning an event can be an overwhelming task. This application can make this process more manageable.

This application is used by:
- Someone wishing to host an event

##User Stories

- As a user, I want to be able to create and add a guest to the list of guests attending my event.
- As a user, I want to be able to view the list of guests attending my event filtered by their age.
- As a user, I want to be able to know the name, age and gender of a guest in the guest list.
- As a user, I want to be able to remove a guest from the guest list that I no longer want to attend.
- As a user, I want to be able to save my event's current guest list.
- As a user, I want to be able to reload my previously saved guest list when I start the application.



##Phase 4: Task 2
&nbsp;

Tue Mar 29 23:15:44 PDT 2022

Guest added to the event.

&nbsp;

Tue Mar 29 23:16:00 PDT 2022

Guest added to the event.

&nbsp;

Tue Mar 29 23:16:09 PDT 2022

Guest removed from the event.

&nbsp;

Tue Mar 29 23:16:25 PDT 2022

Guest added to the event.


##Phase 4: Task 3
If I had more time to work on the project I would split the MyEvent class into two separate class, HostEvent & GuestList. 
Then add GuestList as a field in the HostEvent class.
This refactoring would increase the cohesion. Although this might not be a problem right now since our program is so
simple, it allows to later on add more functionality and create a more complex application.

Besides, that there won't be much to do that will improve the design since the program is 
so simple. I do think that we should remove the UI classes and only keep the GUI to not cause confusion.
(Both has been kept for purposes of the project). Currently, there are lots of duplicated code in both the HostApp
and the HostAppGUI so if we do intend on keeping both it's best to do some refactoring and create abstract methods.



