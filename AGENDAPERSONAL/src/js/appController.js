/**
 * @license
 * Copyright (c) 2014, 2020, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
/*
 * Your application specific code will go here
 */
require(['knockout', 'ojs/ojbootstrap', 'ojs/ojarraydataprovider', 'ojs/ojknockout', 'ojs/ojtable', 'ojs/ojdialog',
  'ojs/ojinputtext', 'ojs/ojformlayout', 'ojs/ojvalidationgroup', 'ojs/ojbutton', 'ojs/ojdatetimepicker'],
  function (ko, Bootstrap, ArrayDataProvider) {
    function ViewModel() {
      var self = this;

      var eventos = [];

      self.dataprovider = ko.observable();

      self.groupValid = ko.observable();
      self.var_cel = ko.observable();
      self.var_pass = ko.observable();
      self.var_nombre = ko.observable();
      self.var_ApMat = ko.observable();
      self.var_ApPat = ko.observable();
      self.var_celular = ko.observable();
      self.var_fecha = ko.observable();
      self.var_password = ko.observable();
      self.var_descripcion = ko.observable();
      self.var_fechaIn = ko.observable();
      self.var_fechaFin = ko.observable();
      self.var_lugar = ko.observable();

      var idUsuario;

      self.dataprovider(new ArrayDataProvider(eventos, { keyAttributes: 'fechaInicio', implicitSort: [{ attribute: 'fechaInicio', direction: 'ascending' }] }));

      this.myActionFunction = function (event) {
        this.selectedMenuItem(event.target.textContent);
      }.bind(this);

      self.entrar = function () {
        var valid = validarCampos();
        if (valid) {
          login(obtenerEventos);
          document.getElementById('modalDialog1').close();
        }
      }.bind(this);

      self.registrarEvento = function () {
        var valid = validarCampos();
        if (valid) {
          registrarEvento();
          document.getElementById('modalDialog3').close();
        }
      }.bind(this);

      self.registrarCuenta = function () {
        var valid = validarCampos();
        if (valid) {
          registrarUsuario();
          document.getElementById('modalDialog2').close();
        }
      };

      self.abrirRegistroDeUsuario = function (event) {
        document.getElementById('modalDialog1').close();
        document.getElementById('modalDialog2').open();
      };

      self.abrirLogin = function (event) {
        document.getElementById('modalDialog2').close();
        document.getElementById('modalDialog1').open();
      };

      self.cancelar = function (event) {
        document.getElementById('modalDialog3').close();
      };

      self.abrirRegistroEvento = function (event) {
        document.getElementById('modalDialog3').open();
      };

      self.cerrarSesion = function (event) {
        var vacio = [];
        self.dataprovider(new ArrayDataProvider(vacio, { keyAttributes: 'fechaInicio', implicitSort: [{ attribute: 'fechaInicio', direction: 'ascending' }] }));
        document.getElementById('modalDialog1').open();
      };

      var validarCampos = function () {
        var tracker = document.getElementById("tracker");
        if (tracker.valid === "valid") {
          return true;
        }
        else {
          tracker.showMessages();
          tracker.focusOn("@firstInvalidShown");
          return false;
        }
      };

      this.open = function (event) {
        document.getElementById('modalDialog1').open();
      };

      function registrarEvento(){
        var request = new XMLHttpRequest();
        console.log(idUsuario);
        var peticion = "descripcion=" + self.var_descripcion() + "&fechaInicio=" + self.var_fechaIn() + "&fechaTermino=" + self.var_fechaFin() + "&lugar=" + self.var_lugar() + "&Usuario_idUsuario=" + idUsuario;
        request.open('POST', "http://localhost:8084/ExamenSegundoParcial/webresources/eventos/registroEvento", true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            var data = JSON.parse(this.response);

            obtenerEventos(idUsuario);

            console.log(data);

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send(peticion);
      }

      function registrarUsuario() {
        var request = new XMLHttpRequest();
        var peticion = "nombre=" + self.var_nombre() + "&apellidoPaterno=" + self.var_ApPat() + "&apellidoMaterno=" + self.var_ApMat() + "&numeroCelular=" + self.var_celular() + "&fechaNacimiento=" + self.var_fecha() + "&password=" + self.var_password();
        request.open('POST', "http://localhost:8084/ExamenSegundoParcial/webresources/usuarios/registroUsuario", true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            var data = JSON.parse(this.response);

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send(peticion);
      }

      function login(funcion) {
        var request = new XMLHttpRequest();
        var peticion = "numeroCelular=" + self.var_cel() + "&password=" + self.var_pass();
        request.open('POST', "http://localhost:8084/ExamenSegundoParcial/webresources/usuarios/login", true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            var data = JSON.parse(this.response);
            idUsuario = data.idUsuario;
            funcion(data.idUsuario);

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send(peticion);
      }

      function obtenerEventos(idUsuario) {
        var request = new XMLHttpRequest();
    
        console.log("El id es: " + idUsuario);
        request.open('GET', "http://localhost:8084/ExamenSegundoParcial/webresources/eventos/obtenerEventosDeUsuario/" + idUsuario, true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            eventos = JSON.parse(this.response);
            self.dataprovider(new ArrayDataProvider(eventos, { keyAttributes: 'fechaInicio', implicitSort: [{ attribute: 'fechaInicio', direction: 'ascending' }]}));

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send();
      }
    }

    $(document).ready(function () {
      document.getElementById('modalDialog1').open();
    })

    var vm = new ViewModel();

    Bootstrap.whenDocumentReady().then(
      function () {
        try{
          ko.applyBindings(vm, document.getElementById('tablaWrapper'));
        }catch{}
        ko.applyBindings(vm, document.getElementById('dialogWrapper'));
        ko.applyBindings(vm, document.getElementById('dialogRegistrarCuenta'));
        ko.applyBindings(vm, document.getElementById('dialogRegistroEvento'));
      }
    );
  });
