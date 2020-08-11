var Riak = require('basho-riak-client');
var assert = require('assert');
var async = require('async');

var people = [{
    emailAddress: "bashoman@basho.com",
    firstName: "Basho",
    lastName: "Man"
}, {
    emailAddress: "johndoe@gmail.com",
    firstName: "John",
    lastName: "Doe"
}];

var nodes = [
    'localhost:8087',
];

function savePeople() {

}

var client = new Riak.Client(nodes, function (err, c) {
    client.ping(function (err, rslt) {
        if (err) {
            throw new Error(err);
        } else {
            // On success, ping returns true
            assert(rslt === true);
        }
    });
    client.stop(function (err, rslt) {
        // NB: you may wish to check err
        process.exit();
    });
});
