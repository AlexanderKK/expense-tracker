let yearlyExpenses = document.querySelectorAll(".yearly-expense")
let yearlyArray = {income: [], expense: [] }
yearlyExpenses.forEach((e)=>{
  e.innerText = Math.floor(Math.random() * 100); // Seed data with random values
  yearlyArray.expense.push(e.innerText)
})

let yearlyIncomes = document.querySelectorAll(".yearly-income")
yearlyIncomes.forEach((e)=>{
  e.innerText = Math.floor(Math.random() * 100); // Seed data with random values
  yearlyArray.income.push(e.innerText)
})

let yearlyTableFinished = document.getElementById("yearly-expenses-diagram")
let ctxB = yearlyTableFinished.getContext('2d')
const expenseGradient = ctxB.createLinearGradient(0, 0, 0, 170);
expenseGradient.addColorStop(0.5, "red");
expenseGradient.addColorStop(1, "orange");

const incomeGradient = ctxB.createLinearGradient(0, 0, 0, 170);
incomeGradient.addColorStop(0.5, "limegreen");
incomeGradient.addColorStop(1, "green");

let myBarChart = new Chart(ctxB, {
  type: 'bar', //this denotes tha type of chart

  data: {// values on X-Axis
    labels: ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'],
     datasets: [
      {
        label: "Income",
        data: yearlyArray.income,
        backgroundColor: expenseGradient
      },
      {
        label: "Expenses",
        data: yearlyArray.expense,
        backgroundColor: incomeGradient
      }  
    ]
  },
  options: {
    aspectRatio: 2
  }
  
})

function monthlyExpensesLoad() {
  setTimeout(() => {
    let monthlyTable = document.getElementById("monthly-table")
    let monthlyTableLen = monthlyTable.rows.length
    let monthlyData = {labels: [], population: []}

    for (let i = 1; i < monthlyTableLen; i++) {

      monthlyData.labels.push(monthlyTable.rows[i].cells[0].innerText)
      monthlyData.population.push(monthlyTable.rows[i].cells[1].innerText)
    }
    let montlyTableFinished = document.getElementById("monthly-expenses-diagram")
    let ctxP = montlyTableFinished.getContext('2d')
    let myPieChart = new Chart(ctxP, {
      type: 'pie',
      data: {
        labels: monthlyData.labels,
        datasets: [{
          data: monthlyData.population,
          backgroundColor: ["#64B5F6", "#FFD54F", "#2196F3", "#FFC107", "#1976D2", "#FFA000", "#0D47A1"],
          hoverBackgroundColor: ["#B2EBF2", "#FFCCBC", "#4DD0E1", "#FF8A65", "#00BCD4", "#FF5722", "#0097A7"]
        }]
      },
      options: {
        aspectRatio: 1.5,
        legend: {
          display: true,
          position: "right"
        }
      }
    });
  }, 1000);
}
