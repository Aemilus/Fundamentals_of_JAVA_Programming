Exercise01
- Build a simple GUI application which allows inserting and deleting 
  persons detail records into a database similar to an contacts.

Requirements
  - `agenda_db`database will have a `person_t` table with bellow structure
    - `p_id` primary key
    - `p_name` is a `VARCHAR(40)` field
    - `p_age` is a `TINYINT(3)` field
    
Result of current implementation

![setup contacts database](doc/setup_agenda_db.png)

![contacts app launch](doc/agenda_launch.png)

![contacts add contact button press](doc/agenda_dialog.png)

![contacts submit person](doc/agenda_submit.png)

![contacts content before delete](doc/agenda_before_delete.png)

![contacts delete one record](doc/agenda_after_delete.png)
