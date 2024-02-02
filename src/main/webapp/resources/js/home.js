let taskId = null;
document.addEventListener("DOMContentLoaded", function () {
    const draggableCards = document.querySelectorAll(".draggable");
    draggableCards.forEach((card) => {
        card.addEventListener('dragstart', function (event) {
            taskId = card.getAttribute("data-task-id");
            console.log('taskId: ' + taskId);
            event.dataTransfer.setData("taskId", taskId);
            console.log('Drag ended at position:', event.clientX, event.clientY);
        });
    });

    const dropZones = document.querySelectorAll(".drop-zone");

    dropZones.forEach((zone) => {
        zone.addEventListener("dragover", (event) => {
            event.preventDefault();
        });

        zone.addEventListener("drop", (event) => {
            event.preventDefault();
            const taskId = event.dataTransfer.getData("taskId");
            const status = zone.getAttribute("data-status");
            console.log('taskId: ' + taskId)
            console.log('status: ' + status)
            Wicket.Ajax.post({
                u: callbackUrl,
                ep: {taskId: taskId, newStatus: status},
                async: true,
            });
            removeNumericQueryParam();
        });
    });
});

function removeNumericQueryParam() {
    let url = window.location.href;
    let newUrl = url.replace(/\?\d+$/, '');
    window.history.replaceState(null, '', newUrl);
}
