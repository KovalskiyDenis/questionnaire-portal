<template>
    <div class="container">

        <navbar :full-navbar="true"></navbar>

        <div class="d-flex justify-content-center">
            <div class="card mt-5" style="width: 23rem;">
                <div class="card-header bg-white">
                    Change Password
                </div>
                <div class="card-body">
                    <div class="form-group mt-1">
                        <label for="current">Current Password</label>
                        <input id="current" type="password" class="form-control" v-model="currentPassword">
                    </div>
                    <div class="form-group mt-1">
                        <label for="new">New Password</label>
                        <input id="new" type="password" class="form-control" v-model="newPassword">
                    </div>
                    <div class="form-group mt-1">
                        <label for="confirm">Confirm New Password</label>
                        <input id="confirm" type="password" class="form-control" v-model="confirmPassword">
                    </div>

                    <div class="d-flex input-group mt-4">
                        <button type="submit" class="flex-fill btn btn-primary" @click="safeNewPassword">CHANGE</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'

    export default {
        components: {
            Navbar
        },
        data() {
            return {
                currentPassword: null,
                newPassword: null,
                confirmPassword: null
            }
        },
        name: "ChangePasswordPage",
        methods: {
            safeNewPassword() {
                if(this.newPassword === this.confirmPassword) {
                    this.$resource('/changePassword').update({
                        id: this.$store.state.user.id,
                        password: this.currentPassword,
                        newPassword: this.newPassword
                    }).then(result => {
                        if(result.ok) {

                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>