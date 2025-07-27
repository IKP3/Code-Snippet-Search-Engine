# Code-Snippet-Search-Engine

**Keyword Scanner** is a JavaFX desktop application that scans a selected folder for a specified keyword. It recursively searches all files within the folder and displays a summary of where the keyword appears and how many times it occurs in each file. Results are presented in a table within the JavaFX interface.

## Features

- Folder selection using a native file chooser
- Case-sensitive keyword search
- Recursively scans all files in the selected directory
- Counts keyword occurrences line by line in each file
- Displays results in a JavaFX TableView with filename and count
- Skips files that cannot be read (e.g. missing, binary, or permission-restricted files)
- Handles file read errors without interrupting the scan

## Notes

- Keyword matching is case-sensitive (e.g. `Example` and `example` are treated as different words).
- Files that cannot be opened or read are skipped and logged to the console.