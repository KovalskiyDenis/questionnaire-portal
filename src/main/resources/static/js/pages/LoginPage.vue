<template>
        <div class="container">
            <div class="d-flex justify-content-center">
                <div class="card mt-5" style="width: 23rem;">
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <logo-type></logo-type>
                        </div>
                        <div class="row justify-content-center mt-2">
                            <h4>Log In</h4>
                        </div>
                        <div class="mt-2">
                            <input class="form-control mt-1" type="text" placeholder="Email" v-model="email">
                            <div v-if="alerts.emailAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.emailAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="mt-2">
                            <input class="form-control mt-1" type="password" placeholder="Password" v-model="password">
                            <div v-if="alerts.passwordAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.passwordAlerts">{{alert}}</div>
                            </div>
                        </div>

                        <div v-if="badCredentials.length !== 0" class="alert alert-warning mt-2" role="alert">
                            <div v-for="alert in badCredentials">{{alert}}</div>
                        </div>

                        <div class="d-flex justify-content-around mt-2">
                            <div class="form-group form-check">
                                <input type="checkbox" class="form-check-input" id="rememberMe">
                                <label class="form-check-label" for="rememberMe">Remember me</label>
                            </div>
                            <span class="text-primary">Forgot your password?</span>
                        </div>
                        <div class="d-flex">
                            <button class="flex-fill btn btn-primary" @click="login">LOG IN</button>
                        </div>
                        <div class="d-flex justify-content-center">
                            <label>Don't have account?&nbsp;</label>
                            <a href="" @click="showRegistrationPage">Sign Up</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
    import Navbar from "../components/navbar/Navbar.vue"
    import LogoType from "../components/LogoType.vue";

    export default {
        name: "LoginPage",
        data() {
            return {
                email: null,
                password: null,

                alerts: {
                    emailAlerts: [],
                    passwordAlerts: [],
                },

                valid: false,
                badCredentials: []
            }
        },
        components: {LogoType, Navbar},
        methods: {
            login() {

                this.validateForm()
                if(this.valid) {
                    const login = '/auth/login?' + 'email=' + this.email + '&password=' + this.password
                    this.$resource(login).save().then(result => {
                        if(result.ok) {
                            this.$resource('/auth/user').get().then(userResult => {
                                if (result.ok) {
                                    this.$store.state.user = userResult.data.user
                                    this.$router.push("/fields").then()
                                }
                            })
                        }
                    }, result => {
                        if(!result.ok) {
                            this.badCredentials.push('Invalid email or password')
                        }
                    })
                }
            },

            validateForm() {
                this.valid= true

                //email
                this.alerts.emailAlerts = []
                this.badCredentials = []
                if (this.email === '' || this.email == null) {
                    this.alerts.emailAlerts.push('Email is required')
                    this.valid = false
                    return
                } else if (!this.email.match(/@/)) {
                    this.alerts.emailAlerts.push('Type email correctly')
                    this.valid = false
                } else if(this.email.length < 3) {
                    this.alerts.emailAlerts.push('Email can\'t be so short')
                    this.valid = false
                }

                //password
                this.alerts.passwordAlerts = []
                if(this.password === '' || this.password == null) {
                    this.alerts.passwordAlerts.push('Password is required')
                    this.valid = false
                }
            },

            showRegistrationPage() {
                this.$router.push("/registration")
            }
        }
    }
</script>

<style scoped>

</style>