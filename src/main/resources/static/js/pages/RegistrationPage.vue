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
                            <input class="form-control" type="email" placeholder="Email" v-model="email">
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="password" placeholder="Password" v-model="password">
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="password" placeholder="Confirm Password" v-model="confirmPassword">
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="text" placeholder="First Name" v-model="firstName">
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="text" placeholder="Last Name" v-model="lastName">
                        </div>
                        <div class="mt-2">
                            <input class="form-control" placeholder="Phone Number" v-model="phoneNumber">
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
    import axios from 'axios'
    export default {
        components: {
            LogoType
        },
        data() {
            return{
                email: null,
                password: null,
                confirmPassword: null,
                firstName: null,
                lastName: null,
                phoneNumber: null
            }
        },
        name: "RegistrationPage",
        methods: {
            registry() {
                if(this.password == this.confirmPassword) {
                    this.$resource('/accounting/registry').save({email: this.email, password: this.password, firstName: this.firstName, lastName: this.lastName, phoneNumber: this.phoneNumber})
                    this.$router.push('/login')
                } else {
                    console.log("diff")
                }
            },
            showLoginPage() {
                this.$router.push('/login')
            }
        }
    }
</script>

<style scoped>

</style>