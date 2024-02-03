document.getElementById('schedulingForm').addEventListener('submit', function(event) {
    // Prevent the form from being submitted normally
    event.preventDefault();

    // Get the name, burst time, priority, and arrival time for each process from the form
    let processDivs = document.querySelectorAll('.process');
    let processes = Array.from(processDivs).map(div => ({
        name: div.querySelector('.name').value,
        burstTime: parseInt(div.querySelector('.burst').value, 10),
        priority: parseInt(div.querySelector('.priority').value, 10),
        arrivalTime: parseInt(div.querySelector('.arrival').value, 10)
    }));

    // Schedule the processes and display the results
    let results = scheduleProcesses(processes);
    displayResults(results, processes);
});

document.getElementById('addProcess').addEventListener('click', function() {
    // Create a new process div
    let processDiv = document.createElement('div');
    processDiv.className = 'process';
    processDiv.innerHTML = `
        <label>Process Name:</label> <input type="text" class="name">
        Burst Time <input type="number" class="burst">
        Priority <input type="number" class="priority">
        Arrival Time <input type="number" class="arrival">
    `;

    // Append the new process div to the processes div
    document.getElementById('processes').appendChild(processDiv);
});

function scheduleProcesses(processes) {
    // Sort the processes by priority (lower number = higher priority)
    processes.sort((a, b) => a.priority - b.priority);

    let time = 0;
    let waitingTimes = [];
    let turnaroundTimes = [];

    for (let i = 0; i < processes.length; i++) {
        // Calculate waiting time for current process
        waitingTimes[i] = i === 0 ? 0 : time;
        // Update time
        time += processes[i].burstTime;
        // Calculate turnaround time for current process
        turnaroundTimes[i] = time;
    }

    // Calculate average waiting and turnaround times
    let avgWaitingTime = waitingTimes.reduce((a, b) => a + b, 0) / processes.length;
    let avgTurnaroundTime = turnaroundTimes.reduce((a, b) => a + b, 0) / processes.length;

    return {
        waitingTimes,
        turnaroundTimes,
        avgWaitingTime,
        avgTurnaroundTime
    };
}

function displayResults(results, processes) {
    // Get a reference to the element where you want to display the results
    let resultsDiv = document.getElementById('results');

    // Clear any previous results
    resultsDiv.innerHTML = '';

    // Create a table
    let table = document.createElement('table');

    // Create a header row
    let headerRow = document.createElement('tr');
    ['Process Name', 'Waiting Time', 'Turnaround Time'].forEach(text => {
        let th = document.createElement('th');
        th.textContent = text;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);

    // Create a row for each process
    for (let i = 0; i < results.waitingTimes.length; i++) {
        let row = document.createElement('tr');

        let nameTd = document.createElement('td');
        nameTd.textContent = processes[i].name;
        row.appendChild(nameTd);

        let waitingTimeTd = document.createElement('td');
        waitingTimeTd.textContent = results.waitingTimes[i];
        row.appendChild(waitingTimeTd);

        let turnaroundTimeTd = document.createElement('td');
        turnaroundTimeTd.textContent = results.turnaroundTimes[i];
        row.appendChild(turnaroundTimeTd);

        table.appendChild(row);
    }

    // Append the table to the results div
    resultsDiv.appendChild(table);

    // Create and append elements for the average waiting and turnaround times
    let avgWaitingTimeP = document.createElement('p');
    avgWaitingTimeP.textContent = 'Average Waiting Time: ' + results.avgWaitingTime;
    resultsDiv.appendChild(avgWaitingTimeP);

    let avgTurnaroundTimeP = document.createElement('p');
    avgTurnaroundTimeP.textContent = 'Average Turnaround Time: ' + results.avgTurnaroundTime;
    resultsDiv.appendChild(avgTurnaroundTimeP);
}