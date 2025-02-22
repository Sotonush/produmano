package com.example.produmano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProdumanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdumanoApplication.class, args);
	}
}
/*единственный момент добавить в GET запросы
 на листы фильтры поиск например и сортировку по датам по названию


 Для управления задачами и комментариями важно иметь полную информацию о сотрудниках, их статусе и контактных данных. Структура сущности «Сотрудник»:
• ID (уникальный идентификатор сотрудника)
• Username (строка — уникальное имя для сотрудника, которое будет использоваться для авторизации и привязки комментариев)
• ФИО (строка — полное имя сотрудника)
• Роль (например, «администратор», «сотрудник», «менеджер»)
• Телефон (строка — контактный номер телефона сотрудника)
• Email (строка — контактная почта сотрудника)
• Должность (строка — должность сотрудника, например, «инженер», «техник» и т.д.)
• Дата присоединения (дата начала работы сотрудника в компании)
• Статус сотрудника (например, «активен», «в отпуске», «уволен»)
 */