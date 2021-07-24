# Albums-MVMM
MVVM project with Dagger2, Room, CoRoutines, Retrofit and unit testing

This is albums android app that makes use of the following API:
Api - https://jsonplaceholder.typicode.com/
List of albums - https://jsonplaceholder.typicode.com/albums

# Recommendations for future improvements
- Imrpove ui by using more apis from https://jsonplaceholder.typicode.com/ 
- As of now we do not have any requirement to update any item of recyclerview after its loaded.
But in future if such requirement comes we can make use of SortedList(androidx.recyclerview.widget.SortedList) 
as it can keep items in order and also notify for changes in the list such that it can be bound to a RecyclerView.Adapter. 
