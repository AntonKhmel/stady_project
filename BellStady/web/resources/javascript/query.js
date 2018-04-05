var prefix = '/BellStady_war_exploded';
// var prefix = '';

var Registration = function() {
    var authenticationView = {
        login: 'Admin',
        password: '123456',
        name: 'Anton'
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/registr',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(authenticationView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var SendActivationCode = function() {
    $.ajax({
        type: 'GET',
        url: prefix + '/api/activation/code',
        contentType:"application/json; charset=utf-8",
        async: true,
        success: function(result) {
            $('#result').text(result.value);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var CheckAuthentication = function() {
    var authenticationView = {
        login: 'user',
        password: 'password'
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/login',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(authenticationView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var FilterOrganizations = function() {
    var organizationView = {
        name: 'Primer'
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/organization/list',
        contentType:"application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(organizationView),
        async: true,
        success: function(result) {
            var res = '';
            for(var i in result) {
                res += result[i].id + '\n' +
                    result[i].name + '\n' +
                    result[i].isActive + '\n\n';
            }
            $('#result').text(res);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var GetOrganizationById = function() {
    $.ajax({
        type: 'GET',
        url: prefix + '/api/organization/2',
        contentType:"application/json; charset=utf-8",
        dataType: 'json',
        async: true,
        success: function(result) {
            $('#result').text(result.id + '\n' +
                result.name + '\n' +
                result.fullName + '\n' +
                result.requisite.inn + '\n' +
                result.requisite.cpp + '\n' +
                result.address.country.name + ', ' +
                result.address.region.name + ', ' +
                result.address.city.name + ', ' +
                result.address.street.name + ', house: ' +
                result.address.house.numberHouse + ', flat: ' +
                result.address.house.numberFlat + ', office: ' +
                result.address.house.numberOffice + '\n' +
                result.phone.number + '\n' +
                result.isActive);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var UpdateOrganization = function() {
    var organizationView = {
        id: 2,
        name: 'newName',
        fullName: 'newFullName',
        requisiteView: {
            id: 2,
            inn: '6464646464',
            cpp: '464646464'
        },
        addressView: {
            countryView: {
                id: 1,
                name: 'newCountry',
                code: 4
            },
            regionView: {
                id: 1,
                name: 'newRegion'
            },
            cityView: {
                id: 1,
                name: 'newCity'
            },
            streetView: {
                id: 1,
                name: 'newStreet'
            },
            houseView: {
                id: 1,
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        phoneView: {
            id: 1,
            number: '89179179797'
        },
        isActive: true
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/organization/update',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(organizationView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var SaveOrganization = function() {
    var organizationView = {
        name: 'Primer',
        fullName: 'newFullName',
        requisiteView: {
            inn: '6464646464',
            cpp: '464646464'
        },
        addressView: {
            countryView: {
                name: 'newCountry',
                code: 4
            },
            regionView: {
                name: 'newRegion'
            },
            cityView: {
                name: 'newCity'
            },
            streetView: {
                name: 'newStreet'
            },
            houseView: {
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        phoneView: {
            number: '89172222222'
        },
        isActive: true
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/organization/save',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(organizationView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var FilterOffices = function() {
    var officeView = {
        organizationView: {
            id: 1
        },
        name: 'Milex',
        isActiv: false
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/office/list',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(officeView),
        async: true,
        success: function(result) {
            var res = '';
            for(var i in result) {
                res += result[i].id + '\n' +
                    result[i].name + '\n' +
                    result[i].isActive + '\n\n';
            }
            $('#result').text(res);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var DeleteOfficeById = function() {
    $.ajax({
        type: 'GET',
        url: prefix + '/api/office/2',
        contentType:"application/json; charset=utf-8",
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var UpdateOffice = function() {
    var officeView = {
        id: 3,
        name: 'Milex',
        addressView: {
            countryView: {
                id: 1,
                name: 'newCountry',
                code: 4
            },
            regionView: {
                id: 1,
                name: 'newRegion'
            },
            cityView: {
                id: 1,
                name: 'newCity'
            },
            streetView: {
                id: 1,
                name: 'newStreet'
            },
            houseView: {
                id: 1,
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        organizationView: {
            id: 1
        },
        phoneView: {
            id: 1,
            number: '89174444444'
        },
        isActive: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/office/update',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(officeView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var SaveOffice = function() {
    var officeView = {
        name: 'Milex',
        addressView: {
            countryView: {
                name: 'newCountry',
                code: 4
            },
            regionView: {
                name: 'newRegion'
            },
            cityView: {
                name: 'newCity'
            },
            streetView: {
                name: 'newStreet'
            },
            houseView: {
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        organizationView: {
            name: 'newName',
            fullName: 'newFullName'
        },
        phoneView: {
            number: '89174444444'
        },
        isActive: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/office/save',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(officeView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var FilterEmployees = function() {
    var employeeView = {
        firstName: 'Ivan',
        secondName: '',
        middleName: '',
        officeView: {id: 1}
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/user/list',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(employeeView),
        async: true,
        success: function(result) {
            var res = '';
            for(var i in result) {
                res += result[i].id + '\n' +
                    result[i].firstName + '\n' +
                    result[i].secondName + '\n' +
                    result[i].middleName + '\n' +
                    result[i].position.name + '\n\n';
            }
            $('#result').text(res);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var DeleteEmployeeById = function() {
    $.ajax({
        type: 'GET',
        url: prefix + '/api/user/3',
        contentType:"application/json; charset=utf-8",
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var UpdateEmployee = function() {
    var employeeView = {
        id: 1,
        firstName: 'Ivan',
        secondName: 'newSecondName',
        middleName: 'newMiddleName',
        positionView: {
            id: 1,
            name: 'newPosition'
        },
        docTypeView: {
            id: 1,
            docCode: 4,
            docName: 'newDocName',
            docNumber: 17,
            docDate: '02.03.2018'
        },
        citizenshipView: {
            id: 1,
            name: 'newCitizenshipName',
            code: 654
        },
        officeView: {id: 1},
        phoneView: {
            id: 1,
            number: '89178888888'
        },
        isIdentified: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/user/update',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(employeeView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var SaveEmployee = function() {
    var employeeView = {
        firstName: 'newFirstName',
        secondName: 'newSecondName',
        middleName: 'newMiddleName',
        positionView: {
            name: 'newPosition'
        },
        docTypeView: {
            docCode: 4,
            docName: 'newDocName',
            docNumber: 17,
            docDate: '02.03.2018'
        },
        citizenshipView: {
            name: 'newCitizenshipName',
            code: 654
        },
        phoneView: {
            number: '89178888888'
        },
        isIdentified: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/user/save',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(employeeView),
        async: true,
        success: function(result) {
            $('#result').text('Success');
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var AllDocs = function() {
    $.ajax({
        type: 'POST',
        url: prefix + '/api/docs',
        contentType:"application/json; charset=utf-8",
        async: true,
        success: function(result) {
            var res = '';
            for(var i in result) {
                res += result[i].docName + '\n' +
                    result[i].docCode + '\n\n';
            }
            $('#result').text(res);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}

var AllCountries = function() {
    $.ajax({
        type: 'POST',
        url: prefix + '/api/countries',
        contentType:"application/json; charset=utf-8",
        async: true,
        success: function(result) {
            var res = '';
            for(var i in result) {
                res += result[i].name + '\n' +
                    result[i].code + '\n\n';
            }
            $('#result').text(res);
            $("html, body").animate({scrollTop:0},"slow")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('#result').text(jqXHR.responseText);
            $("html, body").animate({scrollTop:0},"slow")
        }
    });
}