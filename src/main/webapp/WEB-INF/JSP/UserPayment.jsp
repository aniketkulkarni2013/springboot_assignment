<!DOCTYPE html>
<html>
<head>
    <title>Convert JSON Data to HTML Table</title>
    <style>
        th, td, p, input {
            font:14px Verdana;
        }
        table, th, td
        {
            border: solid 1px #DDD;
            border-collapse: collapse;
            padding: 2px 3px;
            text-align: center;
        }
        th {
            font-weight:bold;
        }
    </style>
</head>
<body>
    <input type="button" onclick="getData()" value="Fetch Data" />
    <p id="showData"></p>
</body>

<script>

    function getData(){
              let xhr = new XMLHttpRequest();

              xhr.open('GET', '/api/getResult');
              // 3. Send the request over the network
              xhr.send();

              // 4. This will be called after the response is received
              xhr.onload = function() {
                if (xhr.status != 200) { // analyze HTTP status of the response

                } else { // show the result
                  alert(`Done, got ${xhr.response.length} bytes`); // responseText is the server
                  createTableFromJSON(JSON.parse(xhr.response));
                }
              };

              xhr.onerror = function() {
                alert("Error occured while fetching data.");
              };
    }

    function createTableFromJSON(searchResult) {
        var col = [];
        for (var i = 0; i < searchResult.length; i++) {
            for (var key in searchResult[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }
        var table = document.createElement("table");
        var tr = table.insertRow(-1);                   // TABLE ROW.

        for (var i = 0; i < col.length; i++) {
            var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = col[i];
            tr.appendChild(th);
        }
        for (var i = 0; i < searchResult.length; i++) {

            tr = table.insertRow(-1);

            for (var j = 0; j < col.length; j++) {
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = searchResult[i][col[j]];
            }
        }
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
    }
</script>
</html>