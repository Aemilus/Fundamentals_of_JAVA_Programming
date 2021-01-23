Java application for workshop 6.

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