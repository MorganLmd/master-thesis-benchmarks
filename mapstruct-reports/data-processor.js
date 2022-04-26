// Put your 20 reports in the same directory
const data1 = require("./data-report-01.json");
const data2 = require("./data-report-02.json");
const data3 = require("./data-report-03.json");
const data4 = require("./data-report-04.json");
const data5 = require("./data-report-05.json");
const data6 = require("./data-report-06.json");
const data7 = require("./data-report-07.json");
const data8 = require("./data-report-08.json");
const data9 = require("./data-report-09.json");
const data10 = require("./data-report-10.json");
const data11 = require("./data-report-11.json");
const data12 = require("./data-report-12.json");
const data13 = require("./data-report-13.json");
const data14 = require("./data-report-14.json");
const data15 = require("./data-report-15.json");
const data16 = require("./data-report-16.json");
const data17 = require("./data-report-17.json");
const data18 = require("./data-report-18.json");
const data19 = require("./data-report-19.json");
const data20 = require("./data-report-20.json");

// data array to process the executions
const dataArray = [
  data1,
  data2,
  data3,
  data4,
  data5,
  data6,
  data7,
  data8,
  data9,
  data10,
  data11,
  data12,
  data13,
  data14,
  data15,
  data16,
  data17,
  data18,
  data19,
  data20,
];

const MICROWATT_TO_WATT = 1000000;
const EXECUTION_NAME = "java";

// to execute the program you need nodeJS and then type into terminal :
// node ./dataProcessor.js
processDataSet(dataArray);

// A DataObject represent the aggregation of the data regarding one process at
// a specific timestamp
function DataObject(
  pHostConsumption,
  pHostTimestamp,
  pPid,
  pExe,
  pProcessConsumption,
  pProcessTimestamp
) {
  this.hostConsumption = pHostConsumption;
  this.hostTimestamp = pHostTimestamp;
  this.pid = pPid;
  this.exe = pExe;
  this.processConsumption = pProcessConsumption;
  this.processTimestamp = pProcessTimestamp;
}

// BenchResult store the information calculated from the data collected for a specific process
function BenchResult(
  pPid,
  pAverageWattConsumption,
  pMaxWattConsumtion,
  pMaxWattPositionIndex,
  pTotalProcessIstantFound,
  pTotalTime,
  pJoules
) {
  (this.pid = pPid),
    (this.averageWattConsumption = pAverageWattConsumption),
    (this.maxWattConsumtion = pMaxWattConsumtion),
    (this.maxWattPositionIndex = pMaxWattPositionIndex),
    (this.totalProcessIstantFound = pTotalProcessIstantFound),
    (this.totalTime = pTotalTime),
    (this.joules = pJoules);
}

// Tostring method to display bench information in a more readible way
BenchResult.prototype.toString = function () {
  return `
    pid = ${this.pid}
    average consumption = ${this.averageWattConsumption.toFixed(5)} watts
    max consumption = ${this.maxWattConsumtion.toFixed(5)} watts
    max consumption found at index ${this.maxWattPositionIndex}
    total instant collected ${this.totalProcessIstantFound}
    bench duration = ${this.totalTime.toFixed(5)}s
    energy consumed = ${this.joules.toFixed(5)} joules
    `;
};

// Object to store the full data set averages
function DataSetResult(
  pAverageWattConsumption,
  pAverageMaxWattConsumption,
  pAverageMaxWattPosition,
  pAverageInstants,
  pAverageDuration,
  pAverageJoules
) {
  (this.averageWattConsumption = pAverageWattConsumption),
    (this.averageMaxWattConsumption = pAverageMaxWattConsumption),
    (this.averageMaxWattPosition = pAverageMaxWattPosition),
    (this.averageInstants = pAverageInstants),
    (this.averageDuration = pAverageDuration),
    (this.averageJoules = pAverageJoules);
}

// Method used to loop over an array of data (execution result)
function processDataSet(dataSet) {
  let resultArray = [];
  for (const data of dataSet) {
    resultArray.push(processData(data));
  }
  // log all executions in the array
  console.log(resultArray);
  calculateAverageOfFullDataSet(resultArray);
}

function calculateAverageOfFullDataSet(dataSet) {
  const averageWattConsumption =
    dataSet.reduce(
      (previousValue, currentValue) =>
        previousValue + currentValue.averageWattConsumption,
      0
    ) / dataSet.length;

  const averageMaxWattConsumption =
    dataSet.reduce(
      (previousValue, currentValue) =>
        previousValue + currentValue.maxWattConsumtion,
      0
    ) / dataSet.length;

  const averageMaxWattPosition =
    dataSet.reduce(
      (previousValue, currentValue) =>
        previousValue + currentValue.maxWattPositionIndex,
      0
    ) / dataSet.length;

  const averageInstants =
    dataSet.reduce(
      (previousValue, currentValue) =>
        previousValue + currentValue.totalProcessIstantFound,
      0
    ) / dataSet.length;

  const averageDuration =
    dataSet.reduce(
      (previousValue, currentValue) => previousValue + currentValue.totalTime,
      0
    ) / dataSet.length;

  const averageJoules =
    dataSet.reduce(
      (previousValue, currentValue) => previousValue + currentValue.joules,
      0
    ) / dataSet.length;

  const dataSetResult = new DataSetResult(
    averageWattConsumption,
    averageMaxWattConsumption,
    averageMaxWattPosition,
    averageInstants,
    averageDuration,
    averageJoules
  );
  console.log(dataSetResult);
}

// Method to aggregate and calculate energy consumption, duration and power
// required for one process (java) in one execution
function processData(fullExecutionData) {
  // Aggregate the data regarding a process named java and store each one
  // in a DataObject
  const filteredData = fullExecutionData
    .map((timePosition) => {
      const filteredJava = timePosition.consumers.filter(
        (arguments) => arguments.exe === EXECUTION_NAME
      );
      return new DataObject(
        timePosition.host.consumption,
        timePosition.host.timestamp,
        filteredJava[0] == undefined ? 0 : filteredJava[0].pid,
        filteredJava[0] == undefined ? 0 : filteredJava[0].exe,
        filteredJava[0] == undefined ? 0 : filteredJava[0].consumption,
        filteredJava[0] == undefined ? 0 : filteredJava[0].timestamp
      );
    })
    .filter((arguments) => arguments.pid > 0); // Filter all the non java process (pid set to 0)

  // We sum the total of microwatt measured, we divide by the length of the arry to find
  // the average microwatt consumed and we divide the result by 1000000 to convert to Watt
  const averageConsumption =
    filteredData.reduce(
      (previousValue, currentValue) =>
        previousValue + currentValue.processConsumption,
      0
    ) /
    filteredData.length /
    MICROWATT_TO_WATT;

  console.log(
    JSON.stringify({
      pid: filteredData[0].pid,
      consumption: filteredData.map((dat) => dat.processConsumption),
    })
  );

  // Calculte the max Watt collected for the process and get its index
  // divide by 1000000 to convert microwatt to watt
  let maxConsumption = filteredData[0].processConsumption;
  let maxConsumptionIndex = 0;
  for (const [index, value] of filteredData.entries()) {
    if (value.processConsumption > maxConsumption) {
      maxConsumption = value.processConsumption;
      maxConsumptionIndex = index;
    }
  }
  maxConsumption /= MICROWATT_TO_WATT;

  // calculte total duration of the process
  // last timestamp found minus first timestamp found
  // result in seconds
  const timeDifference =
    filteredData[filteredData.length - 1].processTimestamp -
    filteredData[0].processTimestamp;
  const processTotalDurationSeconds = timeDifference;

  // calculate the joules consumed to execute the program
  const joulesConsumed = averageConsumption * processTotalDurationSeconds;

  // create a bench result for one execution
  const result = new BenchResult(
    filteredData[0].pid,
    averageConsumption,
    maxConsumption,
    maxConsumptionIndex,
    filteredData.length,
    processTotalDurationSeconds,
    joulesConsumed
  );
  return result;
}
