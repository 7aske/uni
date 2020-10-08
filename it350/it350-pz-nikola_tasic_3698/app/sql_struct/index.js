const fs = require("fs");
const path = require("path");
const Parser = require('sql-ddl-to-json-schema');

const parser = new Parser('mysql');
const filename = process.argv[2];

if (!fs.existsSync(filename)) {
    console.error("File not found");
    process.exit(1);
}
const outfilename = filename.replace(path.extname(filename), ".json");

let sql;

sql = fs.readFileSync(filename).toString();
sql = sql.replace(/^--.*$/mg, '');
sql = sql.replace(/^INSERT.+$/mgi, '');
sql = sql.replace(/^SET.+$/mgi, '');
sql = sql.replace(/^\/\*.+$/mgi, '');
sql = sql.replace(/^\(.+$/mgi, '');
parser.feed(sql);

const parsedJsonFormat = parser.results;

const compactJsonTablesArray = parser.toCompactJson(parsedJsonFormat);

fs.writeFileSync(outfilename, JSON.stringify(compactJsonTablesArray));

console.log(compactJsonTablesArray);
