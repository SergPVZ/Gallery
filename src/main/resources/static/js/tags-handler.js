// Обработчик тегов для поиска
class TagsHandler {
    constructor() {
        this.searchInput = document.querySelector('.search-input');
        this.tagLinks = document.querySelectorAll('.tag-name');
        this.init();
    }

    init() {
        if (!this.searchInput) return;

        this.tagLinks.forEach(link => {
            link.addEventListener('click', this.handleTagClick.bind(this));
        });

        // Инициализация поиска при загрузке
        this.restoreSearch();
    }

    handleTagClick(e) {
        e.preventDefault();
        const tag = e.target.dataset.tag || e.target.textContent.trim();
        this.addTagToSearch(tag);
    }

    addTagToSearch(tag) {
        const currentValue = this.searchInput.value.trim();
        const tags = currentValue ? currentValue.split(' ') : [];

        // Проверяем, нет ли уже такого тега
        if (!tags.includes(tag)) {
            tags.push(tag);
            this.searchInput.value = tags.join(' ');

            // Сохраняем в localStorage
            this.saveSearch();

            // Триггерим событие изменения (для AJAX поиска)
            this.searchInput.dispatchEvent(new Event('input', { bubbles: true }));
        }
    }

    saveSearch() {
        if (this.searchInput) {
            localStorage.setItem('lastSearch', this.searchInput.value);
        }
    }

    restoreSearch() {
        const lastSearch = localStorage.getItem('lastSearch');
        if (lastSearch && this.searchInput) {
            this.searchInput.value = lastSearch;
        }
    }

    clearSearch() {
        if (this.searchInput) {
            this.searchInput.value = '';
            localStorage.removeItem('lastSearch');
            this.searchInput.dispatchEvent(new Event('input', { bubbles: true }));
        }
    }
}

// Инициализация
document.addEventListener('DOMContentLoaded', () => {
    window.tagsHandler = new TagsHandler();
});
