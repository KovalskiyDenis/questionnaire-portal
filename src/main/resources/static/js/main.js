import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import router from 'router/router.js'
// import 'jquery/dist/jquery.min.js'
// import 'popper.js/dist/popper-utils.min.js'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueResource)
Vue.use(BootstrapVue)

new Vue({
    el: '#app',
    router,
    render: a => a(App)
})
