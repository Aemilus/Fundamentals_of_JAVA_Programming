Java application exercise for week 6 of study.

Build the following:
- abstract class ``Animal``:
    - field ``name``
    - constructor with one argument to initialize ``name``
    - abstract method ``feed()``
- interfaces
    - ``Predator`` with a ``hunt()`` method
    - ``Hair`` with a ``hairMotion()`` method
- classes ``Bear``, ``Lion`` and ``Horse`` with the following characteristics:
    - they all extend ``Animal``
    - ``Lion`` and ``Bear`` implement ``Predator`` interface
  - ``Lion`` and ``Horse`` implement ``Hair`` interface
  - each class will have a constructor with one argument to initialize ``name``
  - each class will have one method at developer's choice which does something representative for that specific ``Anymal`` type; for example a ``Lion`` can have a ``roar()`` method
  - each class will have their own specific implementation of ``feed()`` method inherited from ``Animal``
  
Create an array randomly filled with different kinds of ``Animal`` objects.
For each element in array:
- ``feed()`` the ``Animal``
- call the methods of each implemented interface
- call the specific/representative method