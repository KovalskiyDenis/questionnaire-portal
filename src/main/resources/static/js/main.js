import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import router from 'router/router.js'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap/dist/js/bootstrap'
import '@babel/polyfill'
import store from 'store/store'
import {connect} from "./util/websocket";

connect()

Vue.use(VueResource)
Vue.use(BootstrapVue)

new Vue({
    el: '#app',
    router,
    store,
    render: a => a(App)
})
