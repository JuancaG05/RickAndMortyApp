# RickandMortyApp
This is an Android native app written in Kotlin, used as a simple example to test different useful tools for Android projects.

It's inspired in the world of Rick and Morty, so that it gets funnier working with different stuff! 🧪⚗️

The app connects with the GraphQL API in https://rickandmortyapi.com/graphql, and it can do the next stuff:
- Show a list of characters with infinite scroll
- Filter the character list by gender
- Show a caracter details screen when tapping on a character in the list
- Save persistently characters as favourite
- Cache responses from network in-memory, so that the cache is refreshed every time items are requested
- Support deep links like `alkimiirickandmorty://character/{id_character}`

Moreover, it contains unit tests and it's structured with a clean architecture

## App architecture
The app follows a **Clean architecture** approach:

![Clean architecture](https://github.com/user-attachments/assets/9f10a478-bdc6-48c0-b681-e6695fafd54e)
*(Source: from my own)*

This architecture, structured in layers, states that there can only be dependencies towards inner layers, and that the domain is the center of the architecture. That way, we follow the separation-of-concerns principle and in general all
of the SOLID principles, helping increase cohesion and decrease coupling. The result of all of this is a maintainable and quality app.

A brief description of the different layers:
- **UI:** this layer contains the logic related to UI, that is, what user can see. It is divided mainly in 2 parts: *UI components*, which represent visual components, and *State-holders*, which make calculations needed by the UI
and connects with lower layers.
- **Data:** where the actual access to data is made. It mainly consists of *repositories*, which take care of working with different information resources and returning data in a usable state, and *data sources*, which access just one
information source (local or remote)
- **Domain:** in the center of the architecture we can find this layer, which essentially contains the *use cases*, which represent actions made by the user with the app, and the *model*, which is the representation of the domain model of the app.

## Tools used
A list of the different tools and technologies used for different purposes are:
- **Jetpack Compose** for UI
- **Apollo3** as client network for the GraphQL API
- **Koin** for dependency injection
- **Coil** for image display
- **Room** as database manager
- **MockK** for test mocks

## TO-DOs
This is just a kind of PoC (proof of concept)! So, of course, it can be improved in several ways 😀. Some of the proposals for the future are:
- [ ] Structure the app in different modules: right now, different layers are represented in different packages. This is because the app is still a small one, but it would be better to have different modules, that will help with efficiency,
will make the app scale better and make the separation-of-concerns principle be even more fulfilled!
- [ ] Make UI prettier: effort here was not put mainly in UI, so yes, it can be improved a lot
- [ ] Add more unit tests: unit tests for remote data sources or network operations could be added as well, but due to the app structure and how parameters are (or are not) injected, right now is more complex. This could go together with the first
point: structuring the app in different modules to make architecture even better
- [ ] Add UI tests: some UI tests would be nice to have as well!
