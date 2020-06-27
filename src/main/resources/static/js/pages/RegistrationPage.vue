<template>
    <div>
        <div class="container">
            <div class="d-flex justify-content-center">
                <div class="card mt-5" style="width: 23rem;">
                    <div class="card-body">
                        <div class="row justify-content-center">
                            <logo-type></logo-type>
                        </div>
                        <div class="row justify-content-center mt-2">
                            <h4>Sign Up</h4>
                        </div>
                        <div class="mt-2">
                            <input name="email" class="form-control" type="email" placeholder="Email *" v-model="email">
                            <div v-if="alerts.emailAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.emailAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="password" placeholder="Password *" v-model="password">
                            <div v-if="alerts.passwordAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.passwordAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="password" placeholder="Confirm Password *" v-model="confirmPassword">
                            <div v-if="alerts.confirmPasswordAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.confirmPasswordAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="text" placeholder="First Name" v-model="firstName">
                            <div v-if="alerts.firstNameAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.firstNameAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="text" placeholder="Last Name" v-model="lastName">
                            <div v-if="alerts.lastNameAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.lastNameAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="mt-2">
                            <input class="form-control" placeholder="Phone Number" v-model="phoneNumber">
                            <div v-if="alerts.phoneNumberAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                                <div v-for="alert in alerts.phoneNumberAlerts">{{alert}}</div>
                            </div>
                        </div>
                        <div class="d-flex mt-2">
                            <button class="flex-fill btn btn-primary" @click="registry">SIGN UP</button>
                        </div>
                        <div class="d-flex justify-content-center">
                            <label>Already have account?&nbsp;</label>
                            <a href="" @click="showLoginPage">Log in</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import LogoType from 'components/LogoType.vue'
    import { ValidationProvider } from 'vee-validate'

    export default {
        components: {
            LogoType,
            ValidationProvider
        },
        data() {
            return {
                email: null,
                password: null,
                confirmPassword: null,
                firstName: null,
                lastName: null,
                phoneNumber: null,

                alerts: {
                    emailAlerts: [],
                    passwordAlerts: [],
                    confirmPasswordAlerts: [],
                    firstNameAlerts: [],
                    lastNameAlerts: [],
                    phoneNumberAlerts: [],
                },

                valid: true
            }
        },
        name: "RegistrationPage",
        methods: {

            registry() {
                this.validateForm()
                if (this.valid) {
                    this.$resource('/auth/registration').save({
                        email: this.email,
                        password: this.password,
                        firstName: this.firstName,
                        lastName: this.lastName,
                        phoneNumber: this.phoneNumber
                    }).then(result => {
                        if (result.ok) {
                            if(result.data.message === 'Success') {
                                this.$router.push('/login').then()
                            } else {
                                this.alerts.emailAlerts.push("User with this email already exists")
                            }
                        }
                    })
                }
            },

            validateForm() {

                this.valid = true

                //email
                this.alerts.emailAlerts = []
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
                    return
                } else if(this.password.length < 4) {
                    this.alerts.passwordAlerts.push('Minimum length of password is 4 chars')
                    this.valid = false
                }

                //confirmPassword
                this.alerts.confirmPasswordAlerts = []
                if(this.confirmPassword === '' || this.confirmPassword == null) {
                    this.alerts.confirmPasswordAlerts.push('Please confirm password')
                    this.valid = false
                    return
                } else if(this.confirmPassword !== this.password) {
                    this.alerts.confirmPasswordAlerts.push('Passwords are different')
                    this.alerts.passwordAlerts.push('Passwords are different')
                    this.valid = false
                }
            },

            showLoginPage() {
                this.$router.push('/login')
            }
        },
    }
</script>

<style scoped>

</style>