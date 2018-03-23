var prefix = '/BellStady_war_exploded';
// var prefix = '';

var Registration = function() {
    var authentication = {
        login: 'Admin',
        password: '123456',
        name: 'Anton'
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/registr',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(authentication),
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
    var authentication = {
        login: 'user',
        password: 'password'
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/login',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(authentication),
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
    var organization = {
        name: 'Primer'
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/organization/list',
        contentType:"application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(organization),
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
    var organization = {
        id: 2,
        name: 'newName',
        fullName: 'newFullName',
        requisite: {
            id: 2,
            inn: '6464646464',
            cpp: '464646464'
        },
        address: {
            country: {
                id: 1,
                name: 'newCountry',
                code: 4
            },
            region: {
                id: 1,
                name: 'newRegion'
            },
            city: {
                id: 1,
                name: 'newCity'
            },
            street: {
                id: 1,
                name: 'newStreet'
            },
            house: {
                id: 1,
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        phone: {
            id: 1,
            number: '89179179797'
        },
        isActive: true
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/organization/update',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(organization),
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
    var organization = {
        name: 'Primer',
        fullName: 'newFullName',
        requisite: {
            inn: '6464646464',
            cpp: '464646464'
        },
        address: {
            country: {
                name: 'newCountry',
                code: 4
            },
            region: {
                name: 'newRegion'
            },
            city: {
                name: 'newCity'
            },
            street: {
                name: 'newStreet'
            },
            house: {
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        phone: {
            number: '89172222222'
        },
        isActive: true
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/organization/save',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(organization),
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
    var office = {
        organization: {
            id: 1
        },
        name: 'Milex',
        isActiv: false
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/office/list',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(office),
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
    var office = {
        id: 3,
        name: 'Milex',
        address: {
            country: {
                id: 1,
                name: 'newCountry',
                code: 4
            },
            region: {
                id: 1,
                name: 'newRegion'
            },
            city: {
                id: 1,
                name: 'newCity'
            },
            street: {
                id: 1,
                name: 'newStreet'
            },
            house: {
                id: 1,
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        organization: {
            id: 1
        },
        phone: {
            id: 1,
            number: '89174444444'
        },
        isActive: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/office/update',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(office),
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
    var office = {
        name: 'newNameOffice',
        address: {
            country: {
                name: 'newCountry',
                code: 4
            },
            region: {
                name: 'newRegion'
            },
            city: {
                name: 'newCity'
            },
            street: {
                name: 'newStreet'
            },
            house: {
                numberHouse: '21C',
                numberFlat: 32,
                numberOffice: '4A'
            }
        },
        phone: {
            number: '89174444444'
        },
        isActive: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/office/save',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(office),
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
    var employee = {
        firstName: 'Ivan',
        secondName: '',
        middleName: '',
        office: {id: 1}
    };
    $.ajax({
        type: 'POST',
        url: prefix + '/api/user/list',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(employee),
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
    var employee = {
        id: 1,
        firstName: 'Ivan',
        secondName: 'newSecondName',
        middleName: 'newMiddleName',
        position: {
            id: 1,
            name: 'newPosition'
        },
        docType: {
            id: 1,
            docCode: 4,
            docName: 'newDocName',
            docNumber: 17,
            docDate: '02.03.2018'
        },
        citizenship: {
            id: 1,
            name: 'newCitizenshipName',
            code: 654
        },
        office: {id: 1},
        phone: {
            id: 1,
            number: '89178888888'
        },
        isIdentified: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/user/update',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(employee),
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
    var employee = {
        firstName: 'newFirstName',
        secondName: 'newSecondName',
        middleName: 'newMiddleName',
        position: {
            name: 'newPosition'
        },
        docType: {
            docCode: 4,
            docName: 'newDocName',
            docNumber: 17,
            docDate: '02.03.2018'
        },
        citizenship: {
            name: 'newCitizenshipName',
            code: 654
        },
        phone: {
            number: '89178888888'
        },
        isIdentified: true
    }
    $.ajax({
        type: 'POST',
        url: prefix + '/api/user/save',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(employee),
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