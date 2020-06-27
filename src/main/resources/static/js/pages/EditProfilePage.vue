<template>
    <div class="container">

        <navbar :full-navbar="true"></navbar>

        <div class="d-flex justify-content-center">
            <div class="card mt-5" style="width: 23rem;">
                <div class="card-header bg-white">
                    Edit Profile
                </div>
                <div class="card-body">
                    <div class="form-group mt-1">
                        <label for="firstName">First Name</label>
                        <input name="firstName" id="firstName" type="text" class="form-control" v-model="firstName">
                    </div>
                    <div class="form-group mt-1">
                        <label for="lastName">Last Name</label>
                        <input name="lastName" id="lastName" type="text" class="form-control" v-model="lastName">
                    </div>
                    <div class="form-group mt-1">
                        <label for="email">Email *</label>
                        <input name="email" id="email" type="email" class="form-control" v-model="email">
                        <div v-if="alerts.emailAlerts.length !== 0" class="alert alert-warning mt-1" role="alert">
                            <div v-for="alert in alerts.emailAlerts">{{alert}}</div>
                         </div>
                    </div>
                    <div class="form-group mt-1">
                        <label for="phone">Phone Number</label>
                        <input name="phoneNumber" id="phone" type="text" class="form-control" v-model="phoneNumber">
                    </div>
                    <div class="d-flex input-group mt-4">
                        <button type="submit" class="flex-fill btn btn-primary" @click="safeProfile">SAVE</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'

    export default {
        data() {
            return {
                firstName: this.$store.state.user.firstName,
                lastName: this.$store.state.user.lastName,
                email: this.$store.state.user.email,
                phoneNumber: this.$store.state.user.phoneNumber,

                alerts: {
                    emailAlerts: []
                },

                valid: false
            }
        },
        components: {
            Navbar
        },
        name: "EditProfilePage",
        methods: {
            safeProfile() {
                this.validateForm()
                if(this.valid) {
                    this.$resource('/editProfile').update({
                        id: this.$store.state.user.id,
                        email: this.email,
                        firstName: this.firstName,
                        lastName: this.lastName,
                        phoneNumber: this.phoneNumber
                    }).then(result => {
                        if(result.ok) {
                            this.$store.state.user = result.data
                        }
                    })
                }
            },

            validateForm() {
                this.valid = true

                this.alerts.emailAlerts = []
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
            }
        },

    }
</script>

<style scoped>

</style>