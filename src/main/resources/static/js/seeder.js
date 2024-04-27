let expenses = document.querySelector(".expenses-table-content")

let expense = document.createElement("tr");
expense.innerHTML = `<tr>
<td> 1 </td>
<td> Gasoline </td>
<td> $50,00 </td>
<td> Gas </td>
<td> May 15, 2015 </td>
<td>
  <button class="badge badge-gradient-success">Edit <i class="mdi mdi-pencil mdi-32px"></i></button>
              <button class="badge badge-gradient-danger ms-4">Delete <i class="mdi mdi-delete-empty mdi-32px"></i></button>
              </td>
</tr>`

for (let index = 0; index < expenses.rows.length ; index++) {
expenses.appendChild(expense)
}