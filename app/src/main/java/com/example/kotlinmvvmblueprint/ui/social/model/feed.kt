package com.example.kotlinmvvmblueprint.ui.social.model

data class Feed(val id: Int,
                val user: User,
                val content: String,
                val postImage: String,
                var isFavorite: Boolean)

data class User(val id: Int, val name: String, val avatarUrl: String)


val imageList = listOf(
    "https://s3.amazonaws.com/gallerist/products/2276/large/lord-ganesha-3.jpg?1550577226",
    "https://i.pinimg.com/originals/ec/79/e3/ec79e3dc356a2fa4385dc0d1a496fa6b.jpg",
    "https://www.goodmorningimagesforlover.com/wp-content/uploads/2018/09/beautiful-pictures-of-lord-shiva-and-parvati.jpg",
    "https://wallpaperaccess.com/full/1573855.jpg",
    "https://www.hindugodwallpaper.com/images/gods/zoom/833_shiv_parivar_wallpaper_02.jpg",
    "https://i.pinimg.com/236x/5c/37/ec/5c37ecac6a239928645c3b75d4d33110--hd-images-pictures-images.jpg",
    "https://i.pinimg.com/236x/75/8d/a9/758da9d44df6e02817ebd832cad79da3--hindu-deities-hinduism.jpg",
    "https://qph.fs.quoracdn.net/main-qimg-242b665eb68ea16e690ddbb471961632",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS9MB0EZtINpXdo7fYB45XbENzMSB57BawocD5mByRkI96GUAjX",
    "https://www.ecopetit.cat/wpic/mpic/65-655790_lord-vishnu-images-pictures-photos-hd-wallpapers-lord.jpg",
    "https://images-na.ssl-images-amazon.com/images/I/81kIuoikhKL._SL1335_.jpg",
    "https://www.wallsnapy.com/img_gallery/lord-krishna-modern-art-hd-images-for-mobile-9782661.png",
    "https://i.pinimg.com/originals/d0/a6/21/d0a621c5efcf47960cda8dc0cab1bcaf.jpg",
    "https://i.pinimg.com/originals/e6/ea/75/e6ea75aa9a94235a8cb537f4eb53abaa.jpg",
    "https://i.pinimg.com/originals/d0/f9/0f/d0f90f27f66552c3c50d3e82383be207.jpg",
    "https://www.whoa.in/201604-Whoa/the-beautiful-hd-image-of-lord-krishna-and-radhaji.jpg"
)

val contentList = listOf(
    "For selected screen time for kids with a clear purpose of watching learning videos, I recommend Kutuki",
    "We have really had a grest experience with Kutuki. Kids get to learn many things without stressing themselves",
    "If you are tired of your little kid watching endless nonsense on YouTube, try Kutuki. It is very Indian and full of learning.",
    "Very innovative way of teaching simple preschooler concepts through interesting visual and audio aids"
)
val userNameList = listOf<String>(
    "Jagadeesh", "Jyothi", "Poornima", "Hrithik Roshan"
)

val avatarList = listOf(
    "https://i.redd.it/1b7j922tuwm11.jpg",
    "https://godswallpapershd.com/wp-content/uploads/2019/03/vishnu-9.jpg",
    "https://www.itl.cat/pngfile/big/51-510059_vishnu-god.jpg",
    "https://www.kindpng.com/picc/m/539-5399436_balarama-avatar-of-lord-vishnu-hd-png-download.png",
    "https://i.pinimg.com/originals/fc/59/7b/fc597be5c3f42743a8918821d673c363.jpg",
    "https://cdn.shopify.com/s/files/1/0969/9128/products/Baby_Krishna_41e81bb7-668f-49df-b7a4-4c8570e0186e_large.jpg?v=1480160555",
    "https://i.pinimg.com/originals/b9/d2/03/b9d203c3b1714fa4cca464a64dc7cc7a.jpg"
)

fun generateRandomUser(): User = User(
    1,
    userNameList.random(),
    avatarList.random()
)


fun generateRandomFeed(): Feed = Feed(
    1,
    generateRandomUser(),
    contentList.random(),
    imageList.random(),
    false
)

