function getMySqlTable() {
  var resultElement = document.getElementById('tablesResult');
  var tableName = document.getElementById('tableName').value;
  resultElement.innerHTML = '';
  axios.get('http://localhost:8080/getSQLTable/' + tableName)
    .then(function (response) {
      resultElement.innerHTML = generateSuccessHTMLOutput(response);
    })
    .catch(function (error) {
      resultElement.innerHTML = generateErrorHTMLOutput(error);
    });
}

function getMongoDbCollection() {
  var resultElement = document.getElementById('tablesResult');
  var tableName = document.getElementById('tableName').value;
  resultElement.innerHTML = '';
  axios.get('http://localhost:8080/getMongoCollection/' + tableName)
    .then(function (response) {
      resultElement.innerHTML = generateSuccessHTMLOutput(response);
    })
    .catch(function (error) {
      resultElement.innerHTML = generateErrorHTMLOutput(error);
    });
}

function getMaxSalary() {
  var resultElement = document.getElementById('maxSalary');
  resultElement.innerHTML = '';
  axios.get('http://localhost:8080/getMaxSalary')
    .then(function (response) {
      resultElement.innerHTML = generateSuccessHTMLOutput(response);
    })
    .catch(function (error) {
      resultElement.innerHTML = generateErrorHTMLOutput(error);
    });
}

function getNumberOfEmployeesFromCountry() {
  var resultElement = document.getElementById('numberOfEmplyees');
  var countryName = document.getElementById('countryName').value;
  resultElement.innerHTML = '';
  axios.get('http://localhost:8080/getNumberOfEmployeesFromCountry/' + countryName)
    .then(function (response) {
      resultElement.innerHTML = generateSuccessHTMLOutput(response);
    })
    .catch(function (error) {
      resultElement.innerHTML = generateErrorHTMLOutput(error);
    });
}

function getEmailsWithJobTitle() {
  var resultElement = document.getElementById('emailsResults');
  var jobTitle = document.getElementById('jobTitle').value;
  resultElement.innerHTML = '';
  axios.get('http://localhost:8080/getEmailsOfJobTitle/' + jobTitle)
    .then(function (response) {
      resultElement.innerHTML = generateSuccessHTMLOutput(response);
    })
    .catch(function (error) {
      resultElement.innerHTML = generateErrorHTMLOutput(error);
    });
}

function generateSuccessHTMLOutput(response) {
  return  '<h4>Result</h4>' +
          '<h5>Data:</h5>' +
          '<pre>' + JSON.stringify(response.data, null, '\t') + '</pre>';
}
function generateErrorHTMLOutput(error) {
  return  '<h4>Result</h4>' +
          '<h5>Data:</h5>' +
          '<pre>' + JSON.stringify(error.response.data, null, '\t') + '</pre>';
}
