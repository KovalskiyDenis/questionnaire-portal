import Vue from 'vue'
import VueRouter from 'vue-router'
import QuestionnairePage from 'pages/QuestionnairePage.vue'
import LoginPage from 'pages/LoginPage.vue'
import FieldsPage from 'pages/FieldsPage.vue'
import ResponsesPage from 'pages/ResponsesPage.vue'
import EditProfilePage from 'pages/EditProfilePage.vue'
import ChangePasswordPage from 'pages/ChangePasswordPage.vue'
import RegistrationPage from 'pages/RegistrationPage.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/', component: QuestionnairePage},
    {path: '/login', component: LoginPage},
    {path: '/fields', component: FieldsPage},
    {path: '/responses', component: ResponsesPage},
    {path: '/editProfile', component: EditProfilePage},
    {path: '/changePassword', component: ChangePasswordPage},
    {path: '/registration', component: RegistrationPage},
    {path: '*', component: QuestionnairePage}
]

export default new VueRouter({
    mode: 'history',
    routes
})