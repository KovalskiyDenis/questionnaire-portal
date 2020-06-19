<template>
    <div class="container">
        <div class="row">
            <div class="col-auto mr-auto">
                <logo-type></logo-type>
            </div>


            <div class="col-auto align-self-end" v-if="fullNavbar">
                <div class="row">
                    <button class="col btn btn-link bg-white"
                            :class="$route.path === '/fields' ? '' : 'text-secondary'"
                            @click="showFieldsPage">Fields</button>
                    <button class="col btn btn-link bg-white"
                            :class="$route.path === '/responses' ? '' : 'text-secondary'"
                            @click="showResponsesPage">Responses</button>
                    <b-dropdown id="dropdown" toggle-class="text-secondary" variant="white" right text="Responses" class="col">
                        <b-dropdown-item @click="showEditProfilePage">Edit Profile</b-dropdown-item>
                        <b-dropdown-item @click="showChangePasswordPage">Change Password</b-dropdown-item>
                        <b-dropdown-item @click="logout">Log Out</b-dropdown-item>
                    </b-dropdown>
                </div>
            </div>
            <div class="col-auto align-self-end" v-else="fullNavbar">
                <button class="btn btn-link bg-white text-secondary" @click="showLoginPage">Log In</button>
            </div>
        </div>
    </div>
</template>

<script>
    import LogoType from "../LogoType.vue";

    export default {
        props: ['fullNavbar'],
        methods: {
            showLoginPage() {
                this.$router.push('/login')
            },
            showFieldsPage() {
                this.$router.push('/fields')
            },
            showResponsesPage() {
                this.$router.push('/responses')
            },
            showEditProfilePage() {
                this.$router.push('/editProfile')
            },
            showChangePasswordPage(){
                this.$router.push('/changePassword')
            },
            logout() {

            }
        },
        components: {LogoType}
    }
</script>

<style scoped>
</style>