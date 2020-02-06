# Animals

Надо сделать окно с двумя вкладками.
На обеих вкладках отображается список данных (картинка + текст).
По клику на элемент списка открывается детальная страница.
На детальной странице отображается картинка и текст.
API для вкладки 1:
http://kot3.com/xim/api.php?query=cat
API для вкладки 2:
http://kot3.com/xim/api.php?query=dog
При клике кнопки &quot;назад&quot; на детальной - происходит возврат на список элементов.
При клике кнопки &quot;назад&quot; на списке элементов - происходит выход из программы.

1. На каждой вкладке должен быть отдельный fragment.
2. При переходах на детальную страницу и при переключении вкладок в списках должны сохраняться сами данные и
положения скроллов.
3. Приложение должно поддерживать поворот экрана.
После поворота должен сохраниться стек окон, выбранная вкладка, загруженные данные и положения скроллов в самих
списках.
Не использовать retain fragment и setRetainInstance.
У activity не использовать android:configChanges=&quot;orientation|screenSize...&quot;

![Image description](https://github.com/SuleymanovTat/Animals/blob/master/image/main_screen.jpg)
![Image description](https://github.com/SuleymanovTat/Animals/blob/master/image/details_screen.jpg)

