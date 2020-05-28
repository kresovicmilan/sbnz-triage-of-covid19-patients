(this["webpackJsonpsbnz-front"]=this["webpackJsonpsbnz-front"]||[]).push([[0],{19:function(e,t,a){e.exports={headerWrapper:"ImageWrapper_headerWrapper__Ub6nl",header1:"ImageWrapper_header1__3PAiB"}},20:function(e,t,a){e.exports={menu:"Country_menu__38PIG",Form:"Country_Form__1Z-3J"}},21:function(e,t,a){e.exports={Hover:"CountryTable_Hover__3wY98",Table:"CountryTable_Table__-RlFk"}},22:function(e,t,a){e.exports={menu:"Patient_menu__BTDEh",Form:"Patient_Form__35hW5"}},23:function(e,t,a){e.exports={Input:"Input_Input__Cf7w9",InputElement:"Input_InputElement__3d2uK",Invalid:"Input_Invalid__2Ohhx",Label:"Input_Label__1eQnU",CheckBox:"Input_CheckBox__1sBEw"}},24:function(e,t,a){e.exports={menu:"NewUser_menu___3Ezp",Form:"NewUser_Form__2xOBb"}},38:function(e,t,a){e.exports={Modal:"Modal_Modal__1qp2p"}},39:function(e,t,a){e.exports={Backdrop:"Backdrop_Backdrop__20Yvy"}},40:function(e,t,a){e.exports={Hover:"PatientTable_Hover__3jPGq",Table:"PatientTable_Table__1A_rQ"}},41:function(e,t,a){e.exports=a(72)},46:function(e,t,a){},48:function(e,t,a){},66:function(e,t,a){},72:function(e,t,a){"use strict";a.r(t);var n=a(0),l=a.n(n),o=a(35),r=a.n(o),c=(a(46),a(47),a(8)),i=a(1),s=a(2),u=a(3),m=a(4),d=a(6),p=a(5),h=(a(48),a(36)),v=a.n(h).a.create({baseURL:"http://localhost:8080/"}),y=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={username:"",password:""},e.onSubmit=function(t){t.preventDefault();var a=Object(s.a)({},e.state);v.post("api/login",a).then((function(t){console.log(t.data),window.localStorage.setItem("auth",t.data.role),e.props.history.push("/")})).catch((function(e){console.log(e),alert("Login unsuccessful, try again.")}))},e}return Object(m.a)(a,[{key:"render",value:function(){var e=this;return l.a.createElement("div",{className:"container"},l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"col-sm-9 col-md-7 col-lg-5 mx-auto"},l.a.createElement("div",{className:"card card-signin my-5"},l.a.createElement("div",{className:"card-body"},l.a.createElement("h5",{className:"card-title text-center"},"Sign In"),l.a.createElement("form",{className:"form-signin",onSubmit:this.onSubmit},l.a.createElement("div",{className:"row",style:{margin:"15px"}},l.a.createElement("input",{type:"text",id:"username",className:"form-control",placeholder:"Username",value:this.state.username,onChange:function(t){return e.setState({username:t.target.value})}})),l.a.createElement("div",{className:"row",style:{margin:"15px"}},l.a.createElement("input",{type:"password",id:"password",className:"form-control",placeholder:"Password",value:this.state.password,onChange:function(t){return e.setState({password:t.target.value})}})),l.a.createElement("button",{className:"btn btn-lg btn-primary btn-block text-uppercase",type:"submit"},"Sign in"),l.a.createElement("button",{className:"btn btn-lg btn-primary btn-block text-uppercase",type:"button",onClick:function(){return e.props.history.push("/")}},"Back"),l.a.createElement("hr",{className:"my-4"})))))))}}]),a}(n.Component),f=(a(66),function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).onLogout=function(){window.localStorage.removeItem("auth"),window.location.reload()},e}return Object(m.a)(a,[{key:"render",value:function(){return l.a.createElement("div",{className:"wrapper"},l.a.createElement("div",{id:"header",className:"container"}),l.a.createElement("div",{className:"banner-wrapper"},l.a.createElement("div",{id:"banner",className:"container"},l.a.createElement("div",{className:"title"},l.a.createElement("h2",null,"Pacient Triage COVID-19")),l.a.createElement("ul",{className:"actions"},window.localStorage.getItem("auth")?l.a.createElement(l.a.Fragment,null,l.a.createElement("li",null,l.a.createElement("button",{type:"button",className:"button",onClick:this.onLogout},"LOGOUT")),l.a.createElement("li",null,l.a.createElement(c.b,{to:"/country",className:"button"},"ADD NEW COUNTRY")),l.a.createElement("li",null,l.a.createElement(c.b,{to:"/patient",className:"button"},"ADD NEW PATIENT")),2==window.localStorage.getItem("auth")?l.a.createElement("li",null,l.a.createElement(c.b,{to:"/new-user",className:"button"},"ADD NEW USER")):null):l.a.createElement("li",null,l.a.createElement(c.b,{to:"/login",className:"button"},"LOGIN")),"}"))))}}]),a}(n.Component)),b=a(13),g=a(19),E=a.n(g),C=function(){return l.a.createElement("div",{className:E.a.headerWrapper},l.a.createElement("div",{className:E.a.header1+" container"}))},N=a(20),w=a.n(N),x=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={countryName:"",covidPositive:!1,idvIndex:""},e.onSubmit=function(t){t.preventDefault();var a=Object(s.a)({},e.state);v.post("country/add",a).then((function(t){e.props.addNewCountry(t.data),alert("Country added successfully.")})).catch((function(e){console.log(e),alert("Error")}))},e}return Object(m.a)(a,[{key:"render",value:function(){var e=this;return l.a.createElement("div",null,l.a.createElement("form",{onSubmit:this.onSubmit},l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"countryName"},"Country name"),l.a.createElement("input",{type:"text",className:"form-control",id:"countryName",placeholder:"Enter country name",value:this.state.countryName,onChange:function(t){return e.setState({countryName:t.target.value})}})),l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"idvIndex"},"Country IDV"),l.a.createElement("input",{type:"number",className:"form-control",id:"idvIndex",placeholder:"Enter country IDV index",value:this.state.idvIndex,onChange:function(t){return e.setState({idvIndex:t.target.value})}})),l.a.createElement("div",{className:"form-check"},l.a.createElement("input",{type:"checkbox",className:"form-check-input",id:"covidPositive",value:this.state.covidPositive,onChange:function(t){return e.setState({covidPositive:t.target.checked})}}),l.a.createElement("label",{className:"form-check-label",htmlFor:"covidPositive"},"Country is COVID-19 positive")),l.a.createElement("br",null),l.a.createElement("button",{type:"submit",className:"btn btn-primary"},"Submit")))}}]),a}(n.Component),O=a(21),k=a.n(O),j=function(e){var t=null;return t=e.countries?l.a.createElement("table",{className:"table"},l.a.createElement("thead",{className:"thead-dark"},l.a.createElement("tr",null,l.a.createElement("th",{scope:"col"},"#"),l.a.createElement("th",{scope:"col"},"Name"),l.a.createElement("th",{scope:"col"},"Development"),l.a.createElement("th",{scope:"col"},"Covid Positive"))),l.a.createElement("tbody",{className:k.a.Hover},e.countries.map((function(t,a){return l.a.createElement("tr",{key:t.countryName,onClick:function(){return e.openModal(t)}},l.a.createElement("th",{scope:"row"},a+1),l.a.createElement("td",null,t.countryName),l.a.createElement("td",null,t.countryDevelopmentLevel),l.a.createElement("td",null,t.covidPositive?"YES":"NO"))})))):l.a.createElement("p",null,"Loading..."),l.a.createElement("div",{className:k.a.Table},t)},_=a(38),S=a.n(_),T=a(39),I=a.n(T),P=function(e){return e.show?l.a.createElement("div",{className:I.a.Backdrop,onClick:e.clicked}):null},H=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){return Object(u.a)(this,a),t.apply(this,arguments)}return Object(m.a)(a,[{key:"shouldComponentUpdate",value:function(e,t){return e.show!==this.props.show||e.children!==this.props.children}},{key:"render",value:function(){return l.a.createElement(l.a.Fragment,null,l.a.createElement(P,{show:this.props.show,clicked:this.props.modalClosed}),l.a.createElement("div",{className:S.a.Modal,style:{transform:this.props.show?"translateY(0)":"translateY(-100vh)",opacity:this.props.show?"1":"0"}},this.props.children))}}]),a}(n.Component),A=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={countryName:e.props.country.countryName,covidPositive:e.props.country.covidPositive,idvIndex:e.props.country.idvIndex},e}return Object(m.a)(a,[{key:"render",value:function(){var e=this;return this.onSubmit=function(t){t.preventDefault();var a=Object(s.a)(Object(s.a)({},e.state),{},{id:e.props.country.id});v.post("country/modify",a).then((function(t){e.props.updateCountry(t.data)})).catch((function(e){return console.log(e)}))},l.a.createElement("div",null,l.a.createElement("form",{onSubmit:this.onSubmit},l.a.createElement("h5",{style:{textAlign:"center"}},"Edit Country"),l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"countryNameEdit"},"Country name"),l.a.createElement("input",{type:"text",className:"form-control",id:"countryNameEdit",placeholder:"Enter country name",value:this.state.countryName,onChange:function(t){return e.setState({countryName:t.target.value})}})),l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"idvIndexEdit"},"Country IDV"),l.a.createElement("input",{type:"number",className:"form-control",id:"idvIndexEdit",placeholder:"Enter country IDV index",value:this.state.idvIndex,onChange:function(t){return e.setState({idvIndex:t.target.value})}})),l.a.createElement("div",{className:"form-check"},l.a.createElement("input",{type:"checkbox",className:"form-check-input",id:"covidPositiveEdit",checked:this.state.covidPositive,onChange:function(t){return e.setState({covidPositive:t.target.checked})}}),l.a.createElement("label",{className:"form-check-label",htmlFor:"covidPositiveEdit"},"Country is COVID-19 positive")),l.a.createElement("br",null),l.a.createElement("button",{type:"submit",className:"btn btn-primary",style:{margin:"0px 8px"}},"Accept"),l.a.createElement("button",{type:"button",className:"btn btn-warning",style:{margin:"0px 8px"},onClick:this.props.closeModal},"Back")))}}]),a}(n.Component),F=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={countries:null,modalOpen:!1,selectedCountry:null},e.openModal=function(t){e.setState({modalOpen:!0,selectedCountry:t})},e.closeModal=function(){e.setState({modalOpen:!1})},e.addNewCountry=function(t){var a=Object(b.a)(e.state.countries);a.push(t),e.setState({countries:a})},e.updateCountry=function(t){for(var a=0;a<e.state.countries.length;a++)if(t.id===e.state.countries[a].id){var n=Object(b.a)(e.state.countries);n[a]=t,e.setState({countries:n})}e.closeModal()},e}return Object(m.a)(a,[{key:"componentDidMount",value:function(){var e=this;v.get("country/get/all").then((function(t){e.setState({countries:t.data})})).catch((function(e){return console.log(e)}))}},{key:"render",value:function(){var e=this;return l.a.createElement("div",null,l.a.createElement(C,null),l.a.createElement("div",{className:w.a.menu},l.a.createElement("ul",null,l.a.createElement("li",null,l.a.createElement(c.b,{to:"/"},"Home")))),l.a.createElement("hr",null),l.a.createElement("div",{className:"container"},l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:w.a.Form+" col"},l.a.createElement("h5",{style:{textAlign:"center"}},"Add new country"),l.a.createElement(x,{addNewCountry:this.addNewCountry})),l.a.createElement("div",{className:"col"},l.a.createElement("h5",{style:{textAlign:"center"}},"Country list"),l.a.createElement(j,{openModal:this.openModal,countries:this.state.countries})))),l.a.createElement(H,{show:this.state.modalOpen,modalClosed:function(){return e.closeModal()}},this.state.selectedCountry?l.a.createElement(A,{country:this.state.selectedCountry,updateCountry:this.updateCountry,closeModal:this.closeModal}):null))}}]),a}(n.Component),D=a(22),M=a.n(D),L=a(23),V=a.n(L),B=function(e){var t=null,a=[V.a.inputElement];switch(e.invalid&&e.shouldValidate&&e.touched&&a.push(V.a.Invalid),e.elementType){case"input":t=l.a.createElement("input",Object.assign({className:a.join(" ")+e.elementConfig.type==="checkbox"?null:" form-control",style:"checkbox"===e.elementConfig.type?{marginTop:"13px"}:null},e.elementConfig,{value:e.value,onChange:e.changed,id:e.id}));break;case"textarea":t=l.a.createElement("textarea",Object.assign({rows:"3",cols:"60",className:a.join(" ")+" form-control"},e.elementConfig,{value:e.value,onChange:e.changed,id:e.id}));break;case"select":t=l.a.createElement("select",{className:a.join(" ")+" form-control",value:e.value,onChange:e.changed,id:e.id},l.a.createElement("option",{value:"",defaultValue:!0,hidden:!0}),e.elementConfig.options.map((function(e){return l.a.createElement("option",{value:e.value,key:e.value},e.displayValue)})));break;default:t=l.a.createElement("input",Object.assign({className:a.join(" ")+" form-control"},e.elementConfig,{value:e.value,onChange:e.changed,id:e.id}))}return l.a.createElement("div",{className:"form-group row"},l.a.createElement("label",{className:"col-form-label",htmlFor:e.id,style:{marginRight:"15px"}},e.label),l.a.createElement("div",null,t))},R=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={form:{firstName:{elementType:"input",elementConfig:{type:"text",placeholder:"Firstname"},validation:{},value:"",valid:!0,touched:!1,label:"Firstname: ",show:!1,loading:!1},lastName:{elementType:"input",elementConfig:{type:"text",placeholder:"Lastname"},validation:{},value:"",valid:!0,touched:!1,label:"Lastname: ",show:!1,loading:!1},country:{elementType:"select",elementConfig:{options:[]},validation:{},value:"",valid:!0,touched:!1,label:"Country:",show:!1,loading:!1},covidPositiveContact:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Had contact with COVID-19: ",show:!1,loading:!1},cold:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has cold: ",show:!1,loading:!1},lastFever:{elementType:"input",elementConfig:{type:"number",placeholder:"Last fever"},validation:{},value:"",valid:!0,touched:!1,label:"Last fever: ",show:!1,loading:!1},respiratoryRate:{elementType:"input",elementConfig:{type:"number",placeholder:"Respiratory rate"},validation:{},value:"",valid:!0,touched:!1,label:"Respiratory rate: ",show:!1,loading:!1},hypoxia:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has hypoxia: ",show:!1,loading:!1},soreThroat:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has sore throat: ",show:!1,loading:!1},cough:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has cough: ",show:!1,loading:!1},dyspnea:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has dyspnea: ",show:!1,loading:!1},tachypnea:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has tachypnea: ",show:!1,loading:!1},alc:{elementType:"input",elementConfig:{type:"number",placeholder:"ALC"},validation:{},value:"",valid:!0,touched:!1,label:"Absolute lymphocite count: ",show:!1,loading:!1},pneumonia:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has pneumonia: ",show:!1,loading:!1},oxygenSaturation:{elementType:"input",elementConfig:{type:"number",placeholder:"Oxygen saturation"},validation:{},value:"",valid:!0,touched:!1,label:"Oxygen saturation: ",show:!1,loading:!1},nonHospitalPneumonia:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has non hospital pneumonia: ",show:!1,loading:!1}}},e.inputChangedHandler=function(t,a){var n=Object(s.a)({},e.state.form),l=Object(s.a)({},n[a]);console.log(l),"checkbox"===l.elementConfig.type?(l.value=t.target.checked,console.log(l.value)):l.value=t.target.value,l.touched=!0,n[a]=l,e.setState({form:n})},e.onFormSubmit=function(t){t.preventDefault();var a={};for(var n in e.state.form)"country"!==n&&(a[n]=e.state.form[n].value);console.log(a)},e}return Object(m.a)(a,[{key:"componentDidMount",value:function(){var e=this;v.get("country/get/all").then((function(t){var a=t.data.map((function(e){return{value:e.countryName,displayValue:e.countryName}})),n=Object(s.a)({},e.state.form),l=Object(s.a)({},n.country),o=Object(s.a)({},l.elementConfig);o.options=Object(b.a)(a),l.elementConfig=o,n.country=l,e.setState({form:n})})).catch((function(e){return console.log(e)}))}},{key:"render",value:function(){var e=this,t=[];for(var a in this.state.form)t.push({id:a,config:this.state.form[a]});var n=t.map((function(t){return l.a.createElement(B,{key:t.id,elementType:t.config.elementType,elementConfig:t.config.elementConfig,value:t.config.value,changed:function(a){return e.inputChangedHandler(a,t.id)},invalid:!t.config.valid,shouldValidate:t.config.validation,touched:t.config.touched,label:t.config.label,id:t.id})}));return l.a.createElement("div",{className:"container"},l.a.createElement("form",{onSubmit:this.onFormSubmit},n,l.a.createElement("div",{className:"row"},l.a.createElement("button",{className:"btn btn-primary",type:"submit"},"Submit"))))}}]),a}(n.Component),U=a(40),W=a.n(U),Y=function(e){var t=null;return t=e.patients?l.a.createElement("table",{className:"table"},l.a.createElement("thead",{className:"thead-dark"},l.a.createElement("tr",null,l.a.createElement("th",{scope:"col"},"#"),l.a.createElement("th",{scope:"col"},"Name"),l.a.createElement("th",{scope:"col"},"Lastname"),l.a.createElement("th",{scope:"col"},"Has Covid"))),l.a.createElement("tbody",{className:W.a.Hover},e.patients.map((function(t,a){return l.a.createElement("tr",{key:t.id,onClick:function(){return e.openModal(t)}},l.a.createElement("th",{scope:"row"},a+1),l.a.createElement("td",null,t.name),l.a.createElement("td",null,t.lastname),l.a.createElement("td",null,"POSITIVE"===t.covidStatus?"YES":"NO"))})))):l.a.createElement("p",null,"Loading..."),l.a.createElement("div",null,t)},G=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={form:{name:{elementType:"input",elementConfig:{type:"text",placeholder:"Firstname"},validation:{},value:e.props.patient.name,valid:!0,touched:!1,label:"Firstname: ",show:!1,loading:!1},lastname:{elementType:"input",elementConfig:{type:"text",placeholder:"Lastname"},validation:{},value:e.props.patient.lastname,valid:!0,touched:!1,label:"Lastname: ",show:!1,loading:!1},country:{elementType:"select",elementConfig:{options:[]},validation:{},value:"",valid:!0,touched:!1,label:"Country:",show:!1,loading:!1},covidPositiveContact:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Had contact with COVID-19: ",show:!1,loading:!1},cold:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has cold: ",show:!1,loading:!1},lastFever:{elementType:"input",elementConfig:{type:"number",placeholder:"Last fever"},validation:{},value:"",valid:!0,touched:!1,label:"Last fever: ",show:!1,loading:!1},respiratoryRate:{elementType:"input",elementConfig:{type:"number",placeholder:"Respiratory rate"},validation:{},value:"",valid:!0,touched:!1,label:"Respiratory rate: ",show:!1,loading:!1},hypoxia:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has hypoxia: ",show:!1,loading:!1},soreThroat:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has sore throat: ",show:!1,loading:!1},cough:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has cough: ",show:!1,loading:!1},dyspnea:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has dyspnea: ",show:!1,loading:!1},tachypnea:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has tachypnea: ",show:!1,loading:!1},alc:{elementType:"input",elementConfig:{type:"number",placeholder:"ALC"},validation:{},value:"",valid:!0,touched:!1,label:"Absolute lymphocite count: ",show:!1,loading:!1},pneumonia:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has pneumonia: ",show:!1,loading:!1},oxygenSaturation:{elementType:"input",elementConfig:{type:"number",placeholder:"Oxygen saturation"},validation:{},value:"",valid:!0,touched:!1,label:"Oxygen saturation: ",show:!1,loading:!1},nonHospitalPneumonia:{elementType:"input",elementConfig:{type:"checkbox"},validation:{},value:!1,valid:!0,touched:!1,label:"Has non hospital pneumonia: ",show:!1,loading:!1}}},e.inputChangedHandler=function(t,a){var n=Object(s.a)({},e.state.form),l=Object(s.a)({},n[a]);console.log(l),"checkbox"===l.elementConfig.type?(l.value=t.target.checked,console.log(l.value)):l.value=t.target.value,l.touched=!0,n[a]=l,e.setState({form:n})},e.onFormSubmit=function(t){t.preventDefault();var a={};for(var n in e.state.form)a[n]=e.state.form[n].value;console.log(a)},e}return Object(m.a)(a,[{key:"componentDidMount",value:function(){var e=this;v.get("country/get/all").then((function(t){var a=t.data.map((function(e){return{value:e.countryName,displayValue:e.countryName}})),n=Object(s.a)({},e.state.form),l=Object(s.a)({},n.country),o=Object(s.a)({},l.elementConfig);o.options=Object(b.a)(a),l.elementConfig=o,n.country=l,e.setState({form:n})})).catch((function(e){return console.log(e)}))}},{key:"render",value:function(){var e=this,t=[];for(var a in this.state.form)t.push({id:a,config:this.state.form[a]});var n=t.map((function(t){return l.a.createElement(B,{key:t.id,elementType:t.config.elementType,elementConfig:t.config.elementConfig,value:t.config.value,changed:function(a){return e.inputChangedHandler(a,t.id)},invalid:!t.config.valid,shouldValidate:t.config.validation,touched:t.config.touched,label:t.config.label,id:t.id})}));return l.a.createElement("div",{className:"container"},l.a.createElement("form",{onSubmit:this.onFormSubmit},l.a.createElement("h5",{style:{textAlign:"center"}},"Edit Patient"),l.a.createElement("hr",null),n,l.a.createElement("hr",null),l.a.createElement("div",{className:"row"},l.a.createElement("button",{className:"btn btn-primary",type:"submit",style:{margin:"0px 8px"}},"Accept"),l.a.createElement("button",{type:"button",className:"btn btn-warning",style:{margin:"0px 8px"},onClick:this.props.closeModal},"Back"))))}}]),a}(n.Component),z=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={patients:[{name:"Ime1",lastname:"Prezime1",covidStatus:"NEGATIVE",id:1}],modalOpen:!1,selectedPatient:null},e.openModal=function(t){e.setState({modalOpen:!0,selectedPatient:t})},e.closeModal=function(){e.setState({modalOpen:!1})},e}return Object(m.a)(a,[{key:"componentDidMount",value:function(){}},{key:"render",value:function(){var e=this;return l.a.createElement("div",null,l.a.createElement(C,null),l.a.createElement("div",{className:M.a.menu},l.a.createElement("ul",null,l.a.createElement("li",null,l.a.createElement(c.b,{to:"/"},"Home")))),l.a.createElement("hr",null),l.a.createElement("div",{className:"container"},l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:M.a.Form+" col"},l.a.createElement("h5",{style:{textAlign:"center"}},"Add new Patient"),l.a.createElement(R,null)),l.a.createElement("div",{className:"col"},l.a.createElement("h5",{style:{textAlign:"center"}},"Patient list"),l.a.createElement(Y,{patients:this.state.patients,openModal:this.openModal})))),l.a.createElement(H,{show:this.state.modalOpen,modalClosed:function(){return e.closeModal()}},this.state.selectedPatient?l.a.createElement(G,{patient:this.state.selectedPatient,closeModal:this.closeModal}):null))}}]),a}(n.Component),J=a(24),q=a.n(J),Q=function(e){Object(d.a)(a,e);var t=Object(p.a)(a);function a(){var e;Object(u.a)(this,a);for(var n=arguments.length,l=new Array(n),o=0;o<n;o++)l[o]=arguments[o];return(e=t.call.apply(t,[this].concat(l))).state={username:"",password:"",role:1},e.onSubmit=function(t){t.preventDefault();var a=Object(s.a)({},e.state);v.post("api/register",a).then((function(e){console.log(e),alert("User registered successfully.")})).catch((function(e){console.log(e),alert("Error")})),console.log(e.state)},e}return Object(m.a)(a,[{key:"render",value:function(){var e=this;return l.a.createElement("div",null,l.a.createElement(C,null),l.a.createElement("div",{className:q.a.menu},l.a.createElement("ul",null,l.a.createElement("li",null,l.a.createElement(c.b,{to:"/"},"Home")))),l.a.createElement("hr",null),l.a.createElement("div",{className:"container",style:{margin:"auto",textAlign:"center"}},l.a.createElement("h5",null,"Register new Doctor or Administrator"),l.a.createElement("div",{className:"container"},l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:q.a.Form+" col"},l.a.createElement("form",{onSubmit:this.onSubmit},l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"username"},"Username"),l.a.createElement("input",{type:"text",className:"form-control",id:"username",placeholder:"Enter username",value:this.state.username,onChange:function(t){return e.setState({username:t.target.value})}})),l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"password"},"Password"),l.a.createElement("input",{type:"password",className:"form-control",id:"password",placeholder:"Enter password",value:this.state.password,onChange:function(t){return e.setState({password:t.target.value})}})),l.a.createElement("div",{className:"form-group"},l.a.createElement("label",{htmlFor:"role"},"Role select"),l.a.createElement("select",{className:"form-control",id:"role",onChange:function(t){return e.setState({role:t.target.value})}},l.a.createElement("option",{value:"1",defaultValue:!0},"Doctor"),l.a.createElement("option",{value:"2"},"Administrator"))),l.a.createElement("br",null),l.a.createElement("button",{type:"submit",className:"btn btn-primary"},"Register")))))))}}]),a}(n.Component);var K=function(e){return l.a.createElement(c.a,null,l.a.createElement(i.c,null,l.a.createElement(i.a,{path:"/login",exact:!0,component:y}),l.a.createElement(i.a,{path:"/country",component:F}),l.a.createElement(i.a,{path:"/patient",component:z}),l.a.createElement(i.a,{path:"/new-user",component:Q}),l.a.createElement(i.a,{path:"/",component:f})))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));r.a.render(l.a.createElement(l.a.StrictMode,null,l.a.createElement(c.a,null,l.a.createElement(K,null))),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[41,1,2]]]);
//# sourceMappingURL=main.60f05554.chunk.js.map