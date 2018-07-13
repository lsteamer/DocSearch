# DocSearch

<img src="/screenshots/DocSearchCover.png" height="300px"/> <br>
Search for doctors around you.
<br>
<img src="/screenshots/1.png" height="400px"/> <img src="/screenshots/2.png" height="400px"/><img src="/screenshots/3.png" height="400px"/>
<br>


# Coder Notes
The Challenge has two modules with MVP architecture.<br>

<br>
There's a List and a Login modules, each with their Activity, their Contract, View Layer and Presenter.
The login is the landing activity.
<img src="/screenshots/architecture1.png"/>
<br>
<br>
The List module contains a Recycler View that shows the list of results.
There's two Utility classes, Utils provides methods and creates classes for the two modules and UrlContents assists in the creation of the different curl calls.
<img src="/screenshots/architecture2.png"/>
<br>
This structure was adapted from the [Android Architecture Blueprints]
(https://github.com/googlesamples/android-architecture/tree/todo-mvp) <br>

# Dependencies
From Google Dependencies it uses support:design and gms:play-services-location and <br>
from third party dependencies it's uses ButterKnife, Retrofit & it's json-gson parser and Glide.<br>
<img src="/screenshots/dependencies.png"/>
<br>
<br>

# Notes on the Challenge
There are two areas in which I wish to further improve the project, Testing (Unit Testing concretely) and Dagger2. Those are high up on my list of topics to learn.
There are other topics (Kotlin, and RxAndroid) that are also really attractive to me, but without context I do not know how I would adapt them to the project. 

Like I mentioned to Christian, I'm highly motivated and I'm willing to invest of my own time to become a great programmer.

That's why, regardless of the result of this application, I hope I can get thorough comments regarding my code, so I can improve on them.

Thank you for your time.
