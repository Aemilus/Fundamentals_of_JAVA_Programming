#!/bin/bash

# set mysql client default password to be used
export MYSQL_PWD="root.."

# erase agenda db related config if present
./eraseAgendaDb.sh

# setup the database
echo "Setting up agenda database."
echo "1. Creating agenda database."
mysql -uroot --table < sql/create_agenda_db.sql
echo "Done."

echo "2. Creating agenda user."
mysql -uroot --table < sql/create_agenda_user.sql
echo "Done."

echo "3. Creating agenda tables."
export MYSQL_PWD="agenda.."
mysql -uagenda --table < sql/create_agenda_tables.sql
echo "Done."

exit 0
