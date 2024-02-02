document.addEventListener('DOMContentLoaded', (event) => {
    let dragged;

    // Function to handle dragstart and dragend events for tasks
    function setupDraggableTasks() {
        document.querySelectorAll('.draggable').forEach(item => {
            item.addEventListener('dragstart', (event) => {
                dragged = event.target;
                event.target.style.opacity = 0.5;
            });

            item.addEventListener('dragend', (event) => {
                event.target.style.opacity = "";
            });
        });
    }

    // Setting up draggable tasks initially
    setupDraggableTasks();

    // Function to handle dragover and drop events for columns
    function setupDropZone(dropZoneId) {
        console.log(dropZoneId + " setted as dropzone")
        const dropZone = document.getElementById(dropZoneId);

        dropZone.addEventListener('dragover', (event) => {
            event.preventDefault(); // Necessary to allow dropping
        });

        dropZone.addEventListener('drop', (event) => {
            event.preventDefault();
            if (dragged) {
                dropZone.appendChild(dragged);
                dragged.style.opacity = "";
                setupDraggableTasks(); // Re-setup draggable tasks after each drop
            }
        });
    }

    // Setting up drop zones for each column
    ['todo-column', 'inprogress-column', 'done-column'].forEach(setupDropZone);
});
