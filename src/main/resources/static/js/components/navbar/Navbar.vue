<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
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
                    <b-dropdown id="dropdown" toggle-class="text-secondary" variant="white" right class="col">
                        <template v-slot:button-content>
                            {{buttonText}}
                        </template>
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
        data() {
            return {
                buttonText: this.$store.state.user ? this.$store.state.user.firstName + ' ' + this.$store.state.user.lastName : ''
            }
        },
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