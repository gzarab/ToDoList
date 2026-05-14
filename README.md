## To Do List

Welcome to my to do list program. The purpose for this is a weekly to do list that can be updated

## Description

A multi boxed windows that represent a day of the week including an extra notes box. it was created to keep track of important tasks through out the week

contains 8 boxes that represent 7 days of the week and a notes box.
each box has a title that contains the day of the week and the date of the week
Top of box has the weekly title
center of box contains text display boxes
bottom contains entry box, status selector, box selector, save and edit button
edit button edits based on date selected in box selector

## Class overview

App: creates 4 ArrayLists:
                myTodo: Recieves entries and statuses and stores them
                displayArea: stores display boxes data
                dayPicker: stores weekday array
                statusPicker: stores statuses array
    Creates GUI for interaction.

List: creates 1 arraylist to store tasks
    toString to return task arraylist as string
    addTask adds text and status to task
    getTask returns tasks
    updateTask compares indexes to update tasks

Task: constructs task
    getText
    getStatus
    returns formatted string
