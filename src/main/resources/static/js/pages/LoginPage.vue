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
                            <h4>Log In</h4>
                        </div>
                        <div class="mt-2">
                            <input class="form-control mt-1" type="text" placeholder="Email" v-model="email">
                        </div>
                        <div class="mt-2">
                            <input class="form-control mt-1" type="password" placeholder="Password" v-model="password">
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
                            <a href="" @click="showRegistrationPage"> Sign In</a>
                        </div>
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
            }
        },
        components: {LogoType, Navbar},
        methods: {
            login() {
                const login = '/auth/login?' + 'email=' + this.email + '&password=' + this.password
                this.$resource(login).save().then(result => {
                    if(result.ok) {
                        this.$resource('/auth/login').get().then(userResult => {
                            if(result.ok) {
                                console.log(userResult)
                                this.$router.push("/fields")
                            }
                        })
                    }
                })
            },
            showRegistrationPage() {
                this.$router.push("/registration")
            }
        }
    }
</script>

<style scoped>

</style>