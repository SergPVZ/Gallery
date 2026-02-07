/**
 * Обработчик поиска по тегам
 */
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.querySelector('.search-input');
    const tagLinks = document.querySelectorAll('.tag-name');

    if (searchInput && tagLinks.length > 0) {
        // Обработка кликов по тегам
        tagLinks.forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();
                const tag = this.getAttribute('data-tag') || this.textContent.trim();
                addTagToSearch(tag);
            });
        });

        // Восстановление поиска из localStorage
        restoreSearch();

        // Сохранение поиска при изменении
        searchInput.addEventListener('input', function() {
            saveSearch();
        });
    }

    function addTagToSearch(tag) {
        const currentValue = searchInput.value.trim();
        const tags = currentValue ? currentValue.split(' ') : [];

        if (!tags.includes(tag)) {
            tags.push(tag);
            searchInput.value = tags.join(' ');

            // Автоматически отправляем форму
            const form = searchInput.closest('form');
            if (form) {
                form.submit();
            }
        }
    }

    function saveSearch() {
        if (searchInput) {
            localStorage.setItem('imageGallerySearch', searchInput.value);
        }
    }

    function restoreSearch() {
        const savedSearch = localStorage.getItem('imageGallerySearch');
        if (savedSearch && searchInput) {
            searchInput.value = savedSearch;
        }
    }
});
